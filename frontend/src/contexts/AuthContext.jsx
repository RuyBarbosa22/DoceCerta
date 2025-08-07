import React, { createContext, useState, useEffect } from 'react';
import api from '../services/api'; 

export const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(null);
  const [loading, setLoading] = useState(true);

  // Ao carregar a aplicação, verifica se já existe um token salvo
  useEffect(() => {
    const storedToken = localStorage.getItem('authToken');
    if (storedToken) {
      // Se encontrar um token, já configura o cabeçalho padrão do axios
      api.defaults.headers.common['Authorization'] = `Bearer ${storedToken}`;
      setToken(storedToken);
    }
    setLoading(false);
  }, []);

  const login = (newToken) => {
    setToken(newToken);
    localStorage.setItem('authToken', newToken);
    // Configura o cabeçalho padrão para as próximas requisições desta sessão
    api.defaults.headers.common['Authorization'] = `Bearer ${newToken}`;
  };

  const logout = () => {
    setToken(null);
    localStorage.removeItem('authToken');
    // Remove o cabeçalho de autorização
    delete api.defaults.headers.common['Authorization'];
  };

  const isAuthenticated = !!token;

  // Evita renderizar o app antes de saber se o usuário já está logado
  if (loading) {
    return <div>Carregando...</div>;
  }

  return (
    <AuthContext.Provider value={{ token, isAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};