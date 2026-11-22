const formulario = document.getElementById("usuarioForm");

formulario.addEventListener("submit", async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const telefone = document.getElementById("telefone").value;
    const senha = document.getElementById("senha").value;
    const confirmacaoSenha = document.getElementById("confirmacaoSenha").value;

    if (senha !== confirmacaoSenha) {
        alert("As senhas não coincidem! Por favor, verifique.");
        return;
    }

    const dadosUsuario = {
        nome: nome,
        email: email,
        telefone: telefone,
        password: senha
    };

    try {
        const resposta = await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dadosUsuario),
        });

        if (resposta.ok) {
            alert("Cadastro realizado com sucesso!");
            formulario.reset(); // Limpa o formulário
        } else {
            alert("Erro ao cadastrar. Verifique se o e-mail já não está em uso.");
        }

        window.location.href = "/login";
    } catch (erro) {
        console.error("Erro de conexão:", erro);
        alert("Não foi possível conectar ao servidor.");
    }
});