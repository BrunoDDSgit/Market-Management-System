import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import Home from './pages/home/formulario.jsx'
import Logo from './elements/logo.jsx'
import './index.css'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Home />
  </StrictMode>,
)