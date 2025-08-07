import React from 'react';

function Dashboard() {
  const handleLogout = () => {
    // Lógica para fazer logout (limpar token, etc.)
    console.log("Usuário deslogado");
    // Redirecionar para a página de login
  };

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Barra Lateral */}
      <aside className="w-64 bg-gray-800 text-white flex flex-col">
        <div className="p-4 border-b border-gray-700">
          <h2 className="text-xl font-bold">Meu Dashboard</h2>
        </div>
        <nav className="flex-1 p-4 space-y-2">
          <a href="#" className="block py-2 px-4 rounded hover:bg-gray-700">Início</a>
          <a href="#" className="block py-2 px-4 rounded hover:bg-gray-700">Meu Perfil</a>
          <a href="#" className="block py-2 px-4 rounded hover:bg-gray-700">Configurações</a>
        </nav>
        <div className="p-4 border-t border-gray-700">
          <button onClick={handleLogout} className="w-full bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
            Sair
          </button>
        </div>
      </aside>

      {/* Conteúdo Principal */}
      <main className="flex-1 p-10">
        <header>
          <h1 className="text-3xl font-bold text-gray-900">Olá, [Nome do Usuário]!</h1>
          <p className="text-gray-600 mt-2">Aqui está um resumo das suas atividades.</p>
        </header>

        {/* Exemplo de widgets/cards do dashboard */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-8">
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h3 className="font-bold text-lg mb-2">Estatística 1</h3>
            <p className="text-gray-700">Conteúdo da estatística...</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h3 className="font-bold text-lg mb-2">Gráfico de Atividades</h3>
            <p className="text-gray-700">Aqui poderia ir um componente de gráfico.</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h3 className="font-bold text-lg mb-2">Notificações Recentes</h3>
            <p className="text-gray-700">Nenhuma notificação nova.</p>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Dashboard;