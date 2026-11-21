const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", () => {
    carregarProdutos();
});

if (!token) {
    window.location.href = "login.html";
}



async function carregarProdutos() {
    try {
        const resposta = await fetch("http://localhost:8080/produtos");

        console.log("Status da requisição:", resposta.status);

        if (!resposta.ok) {
            throw new Error("Erro ao buscar produtos");
        }

        const produtos = await resposta.json();

        console.log("Produtos recebidos:", produtos);

        exibirProdutos(produtos);

    } catch (erro) {
        console.error("Erro ao carregar produtos:", erro);
    }
}

function exibirProdutos(produtos) {
    const tabela = document.getElementById("produtos");

    if (!tabela) {
        console.error("Elemento produtos não encontrado!");
        return;
    }

    tabela.innerHTML = "";

    produtos.forEach(produto => {

        const linha = document.createElement("tr");

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

        tabela.appendChild(linha);
    });
}



function logout() {

    localStorage.removeItem("token");

    window.location.href = "login.html";

}