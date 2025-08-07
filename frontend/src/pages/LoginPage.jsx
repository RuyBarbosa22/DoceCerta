import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import { AuthContext } from '../contexts/AuthContext.jsx';
import { login as apiLogin } from '../services/authService'; 
import doseCertaLogo from '../assets/doseCertaLogo.png';

// Componente da Logo (reutilizado do cadastro)
const Logo = () => (
  <img 
    src={doseCertaLogo} 
    alt="Logo DoseCerta" 
    className="w-24 h-auto mx-auto"
  />
);

function LoginPage() {
  const [identifier, setIdentifier] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setIsLoading(true);

    try {
      const response = await apiLogin(identifier, password);
      login(response.token);
      navigate('/dashboard'); 
    } catch (err) {
      setError('CNPJ/Email ou senha inválidos. Tente novamente.');
      console.error("Erro no login:", err);
    } finally {
      setIsLoading(false);
    }
  };

  // Estilo para os inputs
  const getInputClass = (hasError) => {
    return `w-full px-4 py-2 bg-slate-100 border border-slate-200 rounded-md text-slate-800 placeholder-slate-400 focus:outline-none focus:ring-2 focus:ring-brand-yellow focus:bg-white transition-colors duration-200 ${
      hasError ? "border-red-400 ring-red-200" : "focus:border-brand-yellow"
    }`;
  };

  // Estilo para o background (reutilizado do cadastro)
  const backgroundStyle = {
    backgroundColor: '#f8fafc',
    backgroundImage: 'radial-gradient(circle at center, #e2e8f0 1px, transparent 1px)',
    backgroundSize: '2rem 2rem',
  };

  return (
    <div style={backgroundStyle} className="flex items-center justify-center min-h-screen py-12 px-4">
      <motion.div 
        initial={{ opacity: 0, scale: 0.95 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.5, ease: "easeOut" }}
        className="w-full max-w-md p-8 md:p-10 space-y-6 bg-white rounded-2xl shadow-2xl"
      >
        <div className="text-center">
          <Logo />
          <h2 className="mt-4 text-3xl font-bold text-slate-900">
            Acesse sua Conta
          </h2>
          <p className="mt-2 text-sm text-slate-600">
            Bem-vindo de volta!
          </p>
        </div>

        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="space-y-4">
            <div>
              <input
                id="identifier"
                name="identifier"
                type="text"
                required
                className={getInputClass(!!error)}
                placeholder="CNPJ ou E-mail"
                value={identifier}
                onChange={(e) => setIdentifier(e.target.value)}
              />
            </div>
            <div>
              <input
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                required
                className={getInputClass(!!error)}
                placeholder="Senha"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>

          <div className="space-y-4">
            <AnimatePresence>
              {error && (
                <motion.p
                  initial={{ opacity: 0, height: 0 }}
                  animate={{ opacity: 1, height: 'auto' }}
                  exit={{ opacity: 0, height: 0 }}
                  className="text-center text-sm text-red-600"
                >
                  {error}
                </motion.p>
              )}
            </AnimatePresence>
            <motion.button
              whileHover={{ scale: 1.02 }}
              whileTap={{ scale: 0.98 }}
              type="submit"
              disabled={isLoading}
              className="w-full flex justify-center py-3 px-4 border border-transparent text-base font-medium rounded-lg text-slate-900 bg-brand-yellow hover:bg-brand-yellow-darker focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-brand-yellow disabled:bg-slate-300 disabled:cursor-not-allowed transition-colors"
            >
              {isLoading ? 'Entrando...' : 'Entrar'}
            </motion.button>
          </div>
        </form>

        <p className="mt-6 text-center text-sm text-slate-600">
          Não tem uma conta?{' '}
          <Link to="/cadastro" className="font-medium text-brand-yellow hover:text-brand-yellow-darker">
            Cadastre-se
          </Link>
        </p>
      </motion.div>
    </div>
  );
}

export default LoginPage;
