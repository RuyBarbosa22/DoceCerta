import React from 'react';
import { motion } from 'framer-motion';

const CheckIcon = () => (
    <svg className="w-6 h-6 text-green-500 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5 13l4 4L19 7"></path></svg>
);

const PricingCard = ({ plan, price, features, recommended = false }) => (
    <motion.div
        whileHover={{ y: -10, scale: 1.02 }}
        className={`relative p-8 border rounded-2xl shadow-lg flex flex-col ${recommended ? 'bg-slate-800 text-white border-brand-yellow' : 'bg-white border-slate-200'}`}
    >
        {recommended && (
            <div className="absolute top-0 -translate-y-1/2 left-1/2 -translate-x-1/2 px-4 py-1 text-sm font-semibold text-slate-900 bg-brand-yellow rounded-full">
                Mais Popular
            </div>
        )}
        <h3 className="text-2xl font-bold">{plan}</h3>
        <p className={`mt-4 text-4xl font-bold ${recommended ? 'text-brand-yellow' : 'text-slate-800'}`}>
            {price} <span className="text-lg font-medium text-slate-500">/mês</span>
        </p>
        <ul className="mt-8 space-y-4 flex-grow">
            {features.map((feature, index) => (
                <li key={index} className="flex items-center">
                    <CheckIcon />
                    <span>{feature}</span>
                </li>
            ))}
        </ul>
        <button className={`w-full mt-10 py-3 px-6 font-semibold rounded-lg transition-all duration-300 ${recommended ? 'bg-brand-yellow text-slate-900 hover:bg-brand-yellow-darker' : 'bg-slate-800 text-white hover:bg-slate-900'}`}>
            Começar Agora
        </button>
    </motion.div>
);

export default PricingCard;
