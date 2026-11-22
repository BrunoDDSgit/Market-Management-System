
const formulario = document.getElementById("loginForm");

formulario.addEventListener("submit", async (event) => {
    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("senha").value;

    if (!email || !password) {
        alert("Informe o e-mail e a senha.");
        return;
    }

    const dadosLogin = { email, password };

    try {
        const resposta = await fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dadosLogin)
        });

        if (!resposta.ok) {
            const erro = await resposta.json();
            alert(erro.message || "Não foi possível realizar o login.");
            return;
        }

        const dados = await resposta.json();

        if (!dados.token) {
            throw new Error("Token não recebido pelo servidor.");
        }

        localStorage.setItem("token", dados.token);

        alert("Login realizado com sucesso!");
        window.location.href = "/home";

    } catch (erro) {
        console.error("Erro ao realizar login:", erro);

        if (erro instanceof TypeError) {
            alert("Não foi possível conectar ao servidor.");
        } else {
            alert("Ocorreu um problema inesperado ao realizar o login.");
        }
    }
});
