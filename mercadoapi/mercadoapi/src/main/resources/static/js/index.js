const token = localStorage.getItem("token");

if (!token) {
    window.location.replace("/login");
}

let todosUsuarios = [];
let mostrarTodosUsuarios = false;
let produtoSelecionado = null;
let produtoEmEdicaoId = null;
let cargoUsuario = null;
let nomeUsuario = null;

document.addEventListener("DOMContentLoaded", async () => {

    await carregarUsuarioAtual();

    if (!cargoUsuario) {
        return;
    }

    document.getElementById("cargo-usuario").innerText=cargoUsuario;

    carregarProdutos();
    carregarUsuarios();

    if (new URLSearchParams(window.location.search).get("modal") === "produto") {
        document.getElementById("modalProduto").style.display = "flex";
        carregarCategorias();
    }

    if (cargoUsuario !== "ADMIN") {
        document.getElementById("btnVisualizarUsuarios").style.display="none";
        return;
    }
});

function obterHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

function abrirFormularioProduto() {

    const modal = document.getElementById("modalProduto");

    if (!modal) {
        console.error("Modal não encontrado!");
        return;
    }

    modal.style.display = "flex";

    history.pushState(
        { modal: "produto" },
        "",
        "/?modal=produto"
    );

    carregarCategorias();
}

document.getElementById("formProduto").addEventListener("submit", async (event) => {

    event.preventDefault();

    const produto = {
        nome: document.getElementById("nome").value,
        descricao: document.getElementById("descricao").value,
        codigoBarras: document.getElementById("codigoBarras").value,
        quantidade: Number(document.getElementById("quantidade").value),
        preco: Number(document.getElementById("preco").value),
        desconto: Number(document.getElementById("desconto").value),
        validade: document.getElementById("validade").value || null,
        categoriaId: Number(document.getElementById("categoriaId").value)
    };

    const editando = produtoEmEdicaoId !== null;

    const url = editando
        ? `http://localhost:8080/produtos/${produtoEmEdicaoId}`
        : "http://localhost:8080/produtos";

    const metodo = editando ? "PUT" : "POST";

    try {
        const resposta = await fetch(url, {
            method: metodo,
            headers: obterHeaders(),
            body: JSON.stringify(produto)
        });

        if (!resposta.ok) {
            const mensagemErro = await resposta.text();

            console.error("Status HTTP:", resposta.status);
            console.error("Resposta do servidor:", mensagemErro);

            throw new Error(
                `Erro ${resposta.status}: ${mensagemErro || "Não foi possível salvar o produto."}`
            );
        }

        alert(editando
            ? "Produto modificado com sucesso!"
            : "Produto cadastrado com sucesso!"
        );

        document.getElementById("formProduto").reset();

        produtoEmEdicaoId = null;

        document.querySelector("#modalProduto .modal-header h2").textContent =
            "Adicionar Produto";

        document.querySelector("#formProduto button[type='submit']").textContent =
            "Cadastrar produto";

        fecharFormularioProduto();

        await carregarProdutos();

    } catch (erro) {
        console.error("Erro ao salvar produto:", erro);
        alert(erro.message);
    }
});

function fecharFormularioProduto() {

    produtoEmEdicaoId = null;

    document.querySelector("#modalProduto .modal-header h2").textContent =
        "Adicionar Produto";

    document.querySelector("#formProduto button[type='submit']").textContent =
        "Cadastrar produto";

    if (history.state?.modal === "produto") {
        history.back();
    } else {
        document.getElementById("modalProduto").style.display = "none";
    }
}

window.addEventListener("popstate", () => {

    const modal = document.getElementById("modalProduto");

    modal.style.display = "none";
});

async function carregarProdutos() {
    try {
        const resposta = await fetch("http://localhost:8080/produtos", {
            headers: obterHeaders()
        });

        console.log("Status da requisição:", resposta.status);

        if (!resposta.ok) {
            throw new Error("Erro ao buscar produtos");
        }

        const produtos = await resposta.json();

        console.log("Produtos recebidos:", produtos);

        exibirProdutos(produtos);
        return true;

    } catch (erro) {
        console.error("Erro ao carregar produtos:", erro);
        return false;
    }
}

function exibirProdutos(produtos) {

    const tabela = document.getElementById("produtos");

    if (!tabela) {
        console.error("Elemento produtos não encontrado!");
        return;
    }

    tabela.innerHTML = "";

    produtoSelecionado = null;

    produtos.forEach(produto => {

        const linha = document.createElement("tr");

        linha.dataset.id = produto.id;

        const dados = [
            produto.nome,
            produto.categoria?.nome ?? "Sem categoria",
            produto.quantidade,
            Number(produto.preco).toLocaleString("pt-BR", {
                style: "currency",
                currency: "BRL"
            }),
            `${produto.desconto}%`,
            produto.validade
                ? new Date(produto.validade + "T00:00:00").toLocaleDateString("pt-BR")
                : "Não informada"
        ];

        dados.forEach(valor => {

            const coluna = document.createElement("td");
            coluna.textContent = valor;

            linha.appendChild(coluna);

        });

        linha.addEventListener("click", () => {

            if (produtoSelecionado === produto.id) {
                linha.classList.remove("produto-selecionado");
                produtoSelecionado = null;

                console.log("Nenhum produto selecionado.");
                return;
            }

            document.querySelectorAll("#produtos tr").forEach(item => {
                item.classList.remove("produto-selecionado");
            });

            linha.classList.add("produto-selecionado");
            produtoSelecionado = produto.id;

            console.log("Produto selecionado:", produtoSelecionado);
        });

        tabela.appendChild(linha);

    });

}

async function carregarCategorias() {

    try {
        const resposta = await fetch("http://localhost:8080/categorias", {
            headers: obterHeaders()
        });

        if (!resposta.ok) {
            throw new Error("Erro ao buscar categorias");
        }

        const categorias = await resposta.json();

        const select = document.getElementById("categoriaId");

        select.innerHTML = '<option value="">Selecione uma categoria</option>';

        categorias.forEach(categoria => {

            const opcao = document.createElement("option");

            opcao.value = categoria.id;
            opcao.textContent = categoria.nome;

            select.appendChild(opcao);
        });

    } catch (erro) {
        console.error("Erro ao carregar categorias:", erro);
    }
}

async function carregarUsuarios() {
    const lista = document.getElementById("listaUsuarios");

    try {
        const resposta = await fetch("http://localhost:8080/usuarios", {
            headers: obterHeaders()
        });

        if (resposta.status === 403) {
            lista.innerHTML = `
                <div class="sem-permissao">
                    <h3>Acesso restrito</h3>
                    <p>Você não tem autorização para visualizar os usuários.</p>
                </div>
            `;

            return;
        }

        if (!resposta.ok) {
            throw new Error("Erro ao buscar usuários.");
        }

        todosUsuarios = await resposta.json();

        exibirUsuarios();

    } catch (erro) {
        console.error("Erro ao carregar usuários:", erro);

        lista.innerHTML = `
            <div class="sem-permissao">
                <h3>Não foi possível carregar os usuários</h3>
                <p>Tente novamente mais tarde.</p>
            </div>
        `;
    }
}

function exibirUsuarios() {

    const lista = document.getElementById("listaUsuarios");

    lista.innerHTML = "";

    const usuariosExibidos = mostrarTodosUsuarios
        ? todosUsuarios
        : todosUsuarios.slice(0, 3);

    usuariosExibidos.forEach(usuario => {

        const item = document.createElement("div");
        item.classList.add("usuario-item");

        const informacoes = document.createElement("div");
        informacoes.classList.add("info-usuario");

        const detalhes = document.createElement("div");
        detalhes.classList.add("usuario-info");

        const nome = document.createElement("strong");
        nome.textContent = usuario.nome;

        const cargoTexto = document.createElement("span");
        cargoTexto.textContent = usuario.cargo;

        detalhes.appendChild(nome);
        detalhes.appendChild(cargoTexto);

        informacoes.innerHTML = `
            <svg viewBox="0 0 24 24" fill="none"
                xmlns="http://www.w3.org/2000/svg">
                <path d="M4 21C4 17.47 6.6 14.55 10 14.07
                M15 7C15 9.2 13.2 11 11 11
                C8.8 11 7 9.2 7 7
                C7 4.8 8.8 3 11 3
                C13.2 3 15 4.8 15 7Z"
                stroke="#000" stroke-width="2"
                stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
        `;

        informacoes.appendChild(detalhes);

        const cargo = document.createElement("span");
        cargo.classList.add("cargo");

        const nomeCargo = usuario.cargo.toLowerCase();

        if (nomeCargo.includes("admin")) {
            cargo.classList.add("administrador");
        } else if (nomeCargo.includes("gerente")) {
            cargo.classList.add("gerente");
        } else {
            cargo.classList.add("funcionario");
        }

        cargo.textContent = usuario.cargo;

        item.appendChild(informacoes);
        item.appendChild(cargo);

        lista.appendChild(item);

    });

    const botao = document.getElementById("btnVisualizarUsuarios");

    botao.textContent = mostrarTodosUsuarios
        ? "Mostrar Menos"
        : "Visualizar Todos";

    botao.style.display = "block";
}

document.getElementById("btnVisualizarUsuarios").addEventListener("click", () => {

    mostrarTodosUsuarios = !mostrarTodosUsuarios;

    exibirUsuarios();

});

document.getElementById("btnAdicionar").addEventListener("click", () => {

    if (cargoUsuario !== "ADMIN") {
        alert("Você não tem autorização para cadastrar produtos.");
        return;
    }

    abrirFormularioProduto();

});

document.getElementById("btnExcluir").addEventListener("click", async () => {

    if (cargoUsuario !== "ADMIN") {
        alert("Você não tem autorização para excluir produtos.");
        return;
    }

    if (produtoSelecionado === null) {
        alert("Selecione um produto primeiro!");
        return;
    }

    const confirmar = confirm("Tem certeza que deseja excluir este produto?");

    if (!confirmar) {
        return;
    }

    try {
        const resposta = await fetch(`http://localhost:8080/produtos/${produtoSelecionado}`, {
            method: "DELETE",
            headers: obterHeaders()
        });

        if (!resposta.ok) {
            throw new Error("Não foi possível excluir o produto.");
        }

        alert("Produto excluído com sucesso!");

        produtoSelecionado = null;

        await carregarProdutos();

    } catch (erro) {
        console.error("Erro ao excluir produto:", erro);
        alert("Erro ao excluir produto. Verifique o console.");
    }
});

document.getElementById("btnModificar").addEventListener("click", async () => {

    if (produtoSelecionado === null) {
        alert("Selecione um produto primeiro!");
        return;
    }

    try {
        const resposta = await fetch(`http://localhost:8080/produtos/${produtoSelecionado}`, {
            headers: obterHeaders()
        });

        if (!resposta.ok) {
            throw new Error("Não foi possível buscar o produto.");
        }

        const produto = await resposta.json();

        await carregarCategorias();

        document.getElementById("nome").value = produto.nome;
        document.getElementById("descricao").value = produto.descricao;
        document.getElementById("codigoBarras").value = produto.codigoBarras;
        document.getElementById("quantidade").value = produto.quantidade;
        document.getElementById("preco").value = produto.preco;
        document.getElementById("desconto").value = produto.desconto;
        document.getElementById("validade").value = produto.validade ?? "";

        document.getElementById("categoriaId").value =
            produto.categoria?.id ?? produto.categoriaId;

        produtoEmEdicaoId = produto.id;

        document.querySelector("#modalProduto .modal-header h2").textContent =
            "Modificar produto";

        document.querySelector("#formProduto button[type='submit']").textContent =
            "Salvar alterações";

        document.getElementById("modalProduto").style.display = "flex";

        history.pushState({ modal: "produto" }, "", "/?modal=produto");

    } catch (erro) {
        console.error("Erro ao carregar produto para edição:", erro);
        alert("Não foi possível carregar os dados do produto.");
    }
});

document.getElementById("btnAtualizar").addEventListener("click", async () => {
    const botao = document.getElementById("btnAtualizar");

    botao.disabled = true;
    botao.textContent = "Atualizando...";

    try {
        await carregarProdutos();

    } finally {
        botao.disabled = false;
        botao.textContent = "Atualizar Tabela";
    }
})

async function carregarUsuarioAtual() {
    try {
        const resposta = await fetch("http://localhost:8080/auth/me", {
            headers: obterHeaders()
        });

        if (!resposta.ok) {
            throw new Error("Não foi possível identificar o usuário.");
        }

        const usuario = await resposta.json();

        cargoUsuario = usuario.cargo;
        nomeUsuario = usuario.nome;

        console.log("Usuário logado:", usuario);

    } catch (erro) {
        console.error("Erro ao identificar usuário:", erro);

        localStorage.removeItem("token");
        window.location.replace("/login");
    }
}

function logout() {

    localStorage.removeItem("token");

    window.location.href = "/login";

}