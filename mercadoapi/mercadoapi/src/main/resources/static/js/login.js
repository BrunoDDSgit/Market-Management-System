
const formulario = document.getElementById("loginForm");

formulario.addEventListener("submit", async (event) => {

    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("senha").value;

    const dadosLogin = {
        email: email,
        password: password
    };

    try {
        const resposta = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dadosLogin)
        });

        console.log("Status da resposta:", resposta.status);

        if (!resposta.ok) {
            const erro = await resposta.text();
            console.error("Erro retornado pelo servidor:", erro);
            alert("Erro ao realizar login!");
            return;
        }

        const dados = await resposta.json();

        console.log("Token recebido:", dados.token);

        localStorage.setItem("token", dados.token);

        alert("Login realizado com sucesso!");

        window.location.href = "/home";

    } catch (erro) {
        console.error("Erro:", erro);
        alert("Não foi possível conectar ao servidor.");
    }
});
