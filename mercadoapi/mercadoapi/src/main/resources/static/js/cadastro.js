
const formulario = document.getElementById("usuarioForm");

formulario.addEventListener("submit", async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const telefone = document.getElementById("telefone").value.trim();
    const senha = document.getElementById("senha").value;
    const confirmacaoSenha = document.getElementById("confirmacaoSenha").value;

    if (!nome || !email || !telefone || !senha || !confirmacaoSenha) {
        alert("Preencha todos os campos.");
        return;
    }

    if (senha !== confirmacaoSenha) {
        alert("As senhas não coincidem.");
        return;
    }

    const dadosUsuario = { nome, email, telefone, password: senha };

    try {
        const resposta = await fetch("/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dadosUsuario)
        });

        if (!resposta.ok) {
            const erro = await resposta.json();
            alert(erro.message || "Não foi possível realizar o cadastro.");
            return;
        }

        alert("Cadastro realizado com sucesso!");
        formulario.reset();
        window.location.href = "/login";

    } catch (erro) {
        console.error("Erro ao realizar cadastro:", erro);
        alert("Não foi possível conectar ao servidor.");
    }
});
