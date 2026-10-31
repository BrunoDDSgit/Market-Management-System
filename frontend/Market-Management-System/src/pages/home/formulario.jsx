import { useState } from 'react'
import './style.css'
import Logo from '../../elements/logo'

function Home() {
  const [count, setCount] = useState(0)

  return (
    <main className='main'>
      <img src="/assets/Boxes.png" alt="" className='img-menu' />
      <div className="home-container">
        <Logo />
        <div className='form-container'>
          <form action="">
            <div className='form-text'>
              <h1>Seja Bem Vindo</h1>
              <p>para cadastrar-se, insira as informações abaixo </p>
            </div>
            <div className='campo'>
              <input type="text" id="nome" name="nome" required/>
              <label for="nome">Nome: </label>
            </div>
            <div className='campo'>
              <input type="email" id="email" name="email" required/>
              <label for="email">Email: </label>
            </div>
            <div className='campo'>
              <input type="password" id="senha" name="senha" required/>
              <label for="senha">Senha: </label>
            </div>
            <div className='botoes'>
              <button type="button">Cadastrar</button>
              <button type="reset">Limpar</button>
            </div>
          </form>
        </div>
    </div>
      <div className='triangulo'>

      </div>
    </main>
  )
}

export default Home