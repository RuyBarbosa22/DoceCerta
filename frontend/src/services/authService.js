import axios from 'axios';

// URL base da sua API. Altere se for diferente.
const API_URL = 'http://localhost:8080/api';

/**
 * Função de login que chama o endpoint /auth/login do backend.
 * @param {string} identificador - Pode ser o CNPJ ou o email da empresa.
 * @param {string} senha - A senha em texto puro.
 * @returns {Promise<object>} - A resposta da API, que contém o token.
 */
export const login = async (identificador, senha) => {
  // Note que o nome das chaves 'identificador' e 'senha' devem ser
  // exatamente iguais aos esperados pelo seu LoginRequestDTO no backend.
  const response = await axios.post(`${API_URL}/auth/login`, {
    identificador,
    senha
  });
  return response.data; // Retorna { token: "...", tokenType: "Bearer" }
};