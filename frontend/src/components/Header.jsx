import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import doseCertaLogo from '../assets/doseCertaLogo.png';

// --- Ícones ---
const MenuIcon = () => (
  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16m-7 6h7" />
  </svg>
);

const CloseIcon = () => (
  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
  </svg>
);

const Header = () => {
    const [isOpen, setIsOpen] = useState(false);
    const navLinks = ["Início", "Sobre Nós", "Planos", "Contato"];

    const menuVariants = {
        hidden: { opacity: 0, y: -20 },
        visible: { opacity: 1, y: 0, transition: { staggerChildren: 0.1 } }
    };

    const linkVariants = {
        hidden: { opacity: 0, y: -10 },
        visible: { opacity: 1, y: 0 }
    };

    return (
        <header className="fixed top-0 left-0 w-full bg-white/80 backdrop-blur-md shadow-sm z-50">
            <div className="container mx-auto px-6 py-4 flex justify-between items-center">
                <Link to="/" className="flex items-center space-x-2">
                    <img src={doseCertaLogo} alt="DoseCerta Logo" className="w-10 h-auto" />
                    <span className="text-xl font-bold text-slate-800">DoseCerta</span>
                </Link>

                {/* Menu Desktop */}
                <nav className="hidden md:flex items-center space-x-8">
                    {navLinks.map(link => (
                        <a key={link} href={`#${link.toLowerCase().replace(' ', '-')}`} className="text-slate-600 hover:text-brand-yellow font-medium transition-colors">{link}</a>
                    ))}
                </nav>

                <div className="hidden md:flex items-center space-x-4">
                    <Link to="/login" className="text-slate-600 hover:text-brand-yellow font-medium transition-colors">Login</Link>
                    <Link to="/cadastro" className="px-5 py-2 text-slate-900 font-semibold bg-brand-yellow rounded-lg hover:bg-brand-yellow-darker transition-all duration-300 transform hover:scale-105">
                        Cadastre-se
                    </Link>
                </div>

                {/* Botão do Menu Mobile */}
                <div className="md:hidden">
                    <button onClick={() => setIsOpen(!isOpen)} className="text-slate-800">
                        {isOpen ? <CloseIcon /> : <MenuIcon />}
                    </button>
                </div>
            </div>

            {/* Menu Mobile Dropdown */}
            <AnimatePresence>
                {isOpen && (
                    <motion.div
                        initial={{ opacity: 0, height: 0 }}
                        animate={{ opacity: 1, height: 'auto' }}
                        exit={{ opacity: 0, height: 0 }}
                        className="md:hidden bg-white border-t border-slate-200"
                    >
                        <motion.nav variants={menuVariants} initial="hidden" animate="visible" className="flex flex-col items-center space-y-4 py-6">
                            {navLinks.map(link => (
                                <motion.a variants={linkVariants} key={link} href={`#${link.toLowerCase().replace(' ', '-')}`} onClick={() => setIsOpen(false)} className="text-slate-600 hover:text-brand-yellow font-medium transition-colors">{link}</motion.a>
                            ))}
                            <motion.div variants={linkVariants} className="pt-4 border-t border-slate-200 w-full flex flex-col items-center space-y-4">
                                <Link to="/login" onClick={() => setIsOpen(false)} className="text-slate-600 hover:text-brand-yellow font-medium transition-colors">Login</Link>
                                <Link to="/cadastro" onClick={() => setIsOpen(false)} className="w-4/5 text-center px-5 py-2 text-slate-900 font-semibold bg-brand-yellow rounded-lg hover:bg-brand-yellow-darker transition-all">
                                    Cadastre-se
                                </Link>
                            </motion.div>
                        </motion.nav>
                    </motion.div>
                )}
            </AnimatePresence>
        </header>
    );
};

export default Header;
