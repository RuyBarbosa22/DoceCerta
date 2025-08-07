import React from 'react';
import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';

// Importando os componentes separados da pasta /components
import Header from '../components/Header';
import PricingCard from '../components/PricingCard';

function LandingPage() {
    const backgroundStyle = {
        backgroundColor: '#ffffff',
        backgroundImage: 'radial-gradient(circle at top left, #fff, #f8fafc)',
    };

    return (
        <div style={backgroundStyle} className="text-slate-800">
            <Header />

            <main className="pt-24">
                {/* Seção Hero */}
                <section id="início" className="container mx-auto px-6 py-20 md:py-32 text-center">
                    <motion.h1 
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.6 }}
                        className="text-4xl md:text-6xl font-extrabold text-slate-900"
                    >
                        A dose certa de <span className="text-brand-yellow">inteligência</span> para o seu negócio.
                    </motion.h1>
                    <motion.p 
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.6, delay: 0.2 }}
                        className="mt-6 max-w-2xl mx-auto text-lg text-slate-600"
                    >
                        Transforme seu ponto de venda. Automatize seu estoque, preveja demandas e elimine perdas com nossa plataforma de gestão inteligente.
                    </motion.p>
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.6, delay: 0.4 }} 
                        className="mt-10"
                    >
                        <Link to="/cadastro" className="px-8 py-4 text-lg text-slate-900 font-bold bg-brand-yellow rounded-lg shadow-lg hover:bg-brand-yellow-darker transition-all duration-300 transform hover:scale-105">
                            Comece Agora
                        </Link>
                    </motion.div>
                </section>

                {/* Seção Sobre Nós */}
                <section id="sobre-nós" className="bg-slate-50 py-20">
                    <div className="container mx-auto px-6 text-center">
                        <h2 className="text-3xl md:text-4xl font-bold text-slate-900">Chega de gestão ineficiente.</h2>
                        <p className="mt-4 max-w-3xl mx-auto text-slate-600">
                            O varejo cresce, mas muitos negócios ficam para trás, perdendo lucratividade com estoque parado, reposição tardia e decisões baseadas em achismos. DoseCerta ataca esses problemas na raiz.
                        </p>
                    </div>
                </section>

                {/* Seção de Planos */}
                <section id="planos" className="py-20">
                    <div className="container mx-auto px-6">
                        <div className="text-center mb-12">
                            <h2 className="text-3xl md:text-4xl font-bold text-slate-900">Planos que cabem no seu negócio</h2>
                            <p className="mt-4 text-slate-600">Escolha a dose certa de tecnologia para crescer.</p>
                        </div>
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-10">
                            <PricingCard 
                                plan="Essencial"
                                price="R$ 79"
                                features={["PDV Completo", "Controle de Estoque", "Cadastro de Produtos", "Relatórios Básicos"]}
                            />
                            <PricingCard 
                                plan="Profissional"
                                price="R$ 149"
                                features={["Tudo do Essencial", "Previsão de Demanda com IA", "Sugestões de Reposição", "Alertas Inteligentes"]}
                                recommended={true}
                            />
                            <PricingCard 
                                plan="Empresarial"
                                price="Customizado"
                                features={["Tudo do Profissional", "Gestão de Múltiplas Filiais", "Promoções Automatizadas", "Suporte Dedicado"]}
                            />
                        </div>
                    </div>
                </section>

                {/* Seção de Contato */}
                <section id="contato" className="bg-slate-800 text-white py-20">
                    <div className="container mx-auto px-6 text-center">
                        <h2 className="text-3xl font-bold">Pronto para dar o próximo passo?</h2>
                        <p className="mt-4 max-w-xl mx-auto">Entre em contato e descubra como o DoseCerta pode transformar a gestão do seu varejo.</p>
                        <div className="mt-8">
                            <a href="mailto:contato@dosecerta.com" className="px-8 py-4 text-lg text-slate-900 font-bold bg-brand-yellow rounded-lg shadow-lg hover:bg-brand-yellow-darker transition-all duration-300 transform hover:scale-105">
                                Fale Conosco
                            </a>
                        </div>
                    </div>
                </section>
            </main>

            <footer className="bg-slate-900 text-slate-400 py-8">
                <div className="container mx-auto px-6 text-center">
                    <p>&copy; 2025 DoseCerta. Todos os direitos reservados.</p>
                </div>
            </footer>
        </div>
    );
}

export default LandingPage;
