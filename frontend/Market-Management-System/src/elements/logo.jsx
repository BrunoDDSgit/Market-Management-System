import { useState } from 'react'
import './logo.css'

function Logo() {
  const [count, setCount] = useState(0)

  return (
    <div className='container-logo'>
      <img src="/assets/Logo.png" alt="Logo" className='logo' />
      <img src="/assets/Texto.png" alt="Texto" className='texto-logo' />
    </div>
  )
}

export default Logo