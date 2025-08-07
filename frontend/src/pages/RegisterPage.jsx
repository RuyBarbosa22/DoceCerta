
import React, { useState, useCallback } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useForm, useWatch } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import axios from "axios";
import { motion, AnimatePresence } from "framer-motion";
import PasswordStrengthMeter from "../components/PasswordStrengthMeter";
// 1. Importe sua logo. Coloque o arquivo doseCertaLogo.png na pasta src/assets/
import doseCertaLogo from '../assets/doseCertaLogo.png';

// 2. Novo componente para renderizar a imagem da logo
const Logo = () => (
  <img 
    src={doseCertaLogo} 
    alt="Logo DoseCerta" 
    className="w-24 h-auto mx-auto" // Tamanho aumentado
  />
);

const AnimatedFieldError = ({ message }) => (
  <AnimatePresence>
    {message && (
      <motion.p
        initial={{ opacity: 0, y: -10 }}
        animate={{ opacity: 1, y: 0 }}
        exit={{ opacity: 0, y: -10 }}
        transition={{ duration: 0.2 }}
        className="text-sm text-red-600 mt-1"
      >
        {message}
      </motion.p>
    )}
  </AnimatePresence>
);


const validationSchema = yup.object().shape({
  nome: yup.string().required("O nome da empresa é obrigatório"),
  cnpj: yup.string().required("O CNPJ é obrigatório").matches(/^\d{14}$/, "O CNPJ deve conter 14 dígitos"),
  email: yup.string().email("O e-mail informado é inválido").nullable(),
  senha: yup.string().required("A senha é obrigatória").min(8, "A senha deve ter no mínimo 8 caracteres"),
  confirmarSenha: yup.string().required("Confirme sua senha").oneOf([yup.ref('senha'), null], 'As senhas devem ser iguais'),
  endereco: yup.object().shape({
    cep: yup.string().required("O CEP é obrigatório").matches(/^\d{8}$/, "O CEP deve conter 8 dígitos"),
    rua: yup.string().required("A rua é obrigatória"),
    numero: yup.string().required("O número é obrigatório"),
    bairro: yup.string().required("O bairro é obrigatório"),
    cidade: yup.string().required("A cidade é obrigatória"),
    estado: yup.string().required("O estado é obrigatório").matches(/^[A-Z]{2}$/, "UF inválida"),
  }),
});

function RegisterPage() {
  const [isLoading, setIsLoading] = useState(false);
  const [apiError, setApiError] = useState("");
  const [cepError, setCepError] = useState("");
  const [showSuccess, setShowSuccess] = useState(false);
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    setValue,
    control,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(validationSchema),
  });

  const senhaValue = useWatch({ control, name: "senha" });

  const onSubmit = async (data) => {
    setIsLoading(true);
    setApiError("");
    const { confirmarSenha, ...payload } = data;
    try {
      await axios.post('http://localhost:8080/api/empresas/cadastrar', payload);
      setShowSuccess(true);
      setTimeout(() => navigate('/login'), 2000);
    } catch (error) {
      const errorMsg = error.response?.data?.error || "Ocorreu um erro ao cadastrar. Tente novamente.";
      setApiError(error.response?.status === 409 ? "Este CNPJ já está em uso." : errorMsg);
    } finally {
      setIsLoading(false);
    }
  };

  const handleCepBlur = useCallback(async (e) => {
    const cep = e.target.value.replace(/\D/g, '');
    if (cep.length !== 8) {
        setCepError("");
        return;
    }

    setIsLoading(true);
    setCepError("");
    try {
      const { data } = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
      if (data.erro) {
        setCepError("CEP não encontrado.");
      } else {
        setValue("endereco.rua", data.logradouro, { shouldValidate: true });
        setValue("endereco.bairro", data.bairro, { shouldValidate: true });
        setValue("endereco.cidade", data.localidade, { shouldValidate: true });
        setValue("endereco.estado", data.uf, { shouldValidate: true });
      }
    } catch (error) {
      console.error("Erro ao buscar CEP:", error);
      setCepError("Não foi possível buscar o CEP. Verifique sua conexão.");
    } finally {
      setIsLoading(false);
    }
  }, [setValue]);

  const getInputClass = (fieldName) => {
    const fieldError = fieldName.split('.').reduce((acc, part) => acc && acc[part], errors);
    return `w-full px-4 py-2 bg-slate-100 border border-slate-200 rounded-md text-slate-800 placeholder-slate-400 focus:outline-none focus:ring-2 focus:ring-brand-yellow focus:bg-white transition-colors duration-200 ${
      fieldError ? "border-red-400 ring-red-200" : "focus:border-brand-yellow"
    }`;
  };
  
  const backgroundStyle = {
    backgroundColor: '#f8fafc',
    backgroundImage: 'radial-gradient(circle at center, #e2e8f0 1px, transparent 1px)',
    backgroundSize: '2rem 2rem',
  };

  return (
    <div style={backgroundStyle} className="flex items-center justify-center min-h-screen py-12 px-4 relative overflow-hidden">
      <AnimatePresence>
        {showSuccess && (
          <motion.div
            initial={{ opacity: 0, y: -50 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -50 }}
            className="absolute top-5 left-1/2 -translate-x-1/2 bg-green-500 text-white font-semibold py-3 px-6 rounded-lg shadow-lg z-50"
          >
            Cadastro realizado com sucesso! Redirecionando...
          </motion.div>
        )}
      </AnimatePresence>

      <motion.div 
        initial={{ opacity: 0, scale: 0.95 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.5, ease: "easeOut" }}
        className="w-full max-w-4xl p-8 md:p-10 space-y-6 bg-white rounded-2xl shadow-2xl"
      >
        <div className="text-center">
          {/* 3. Use o novo componente da logo aqui */}
          <Logo />
          <h2 className="mt-4 text-3xl font-bold text-slate-900">
            Crie sua conta DoseCerta
          </h2>
          <p className="mt-2 text-sm text-slate-600">
            Comece a gerenciar seu negócio com precisão.
          </p>
        </div>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-8">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                    <input placeholder="Nome da Empresa *" className={getInputClass("nome")} {...register("nome")} />
                    <AnimatedFieldError message={errors.nome?.message} />
                </div>
                <div>
                    <input placeholder="CNPJ (apenas números) *" className={getInputClass("cnpj")} {...register("cnpj")} />
                    <AnimatedFieldError message={errors.cnpj?.message} />
                </div>
                <div className="md:col-span-2">
                    <input type="email" placeholder="E-mail de contato" className={getInputClass("email")} {...register("email")} />
                    <AnimatedFieldError message={errors.email?.message} />
                </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-6 gap-6">
                <div className="md:col-span-2">
                    <input placeholder="CEP *" className={getInputClass("endereco.cep")} {...register("endereco.cep")} onBlur={handleCepBlur} />
                    <AnimatedFieldError message={errors.endereco?.cep?.message || cepError} />
                </div>
                 <div className="md:col-span-4">
                    <input placeholder="Rua *" className={getInputClass("endereco.rua")} {...register("endereco.rua")} />
                    <AnimatedFieldError message={errors.endereco?.rua?.message} />
                </div>
                <div className="md:col-span-2">
                    <input placeholder="Número *" className={getInputClass("endereco.numero")} {...register("endereco.numero")} />
                    <AnimatedFieldError message={errors.endereco?.numero?.message} />
                </div>
                <div className="md:col-span-4">
                    <input placeholder="Bairro *" className={getInputClass("endereco.bairro")} {...register("endereco.bairro")} />
                    <AnimatedFieldError message={errors.endereco?.bairro?.message} />
                </div>
                <div className="md:col-span-4">
                    <input placeholder="Cidade *" className={getInputClass("endereco.cidade")} {...register("endereco.cidade")} />
                    <AnimatedFieldError message={errors.endereco?.cidade?.message} />
                </div>
                <div className="md:col-span-2">
                    <input placeholder="Estado (UF) *" className={getInputClass("endereco.estado")} {...register("endereco.estado")} />
                    <AnimatedFieldError message={errors.endereco?.estado?.message} />
                </div>
            </div>
            
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <input type="password" placeholder="Crie uma Senha *" className={getInputClass("senha")} {...register("senha")} />
                <PasswordStrengthMeter password={senhaValue || ''} />
                <AnimatedFieldError message={errors.senha?.message} />
              </div>
              <div>
                <input type="password" placeholder="Confirme a Senha *" className={getInputClass("confirmarSenha")} {...register("confirmarSenha")} />
                <AnimatedFieldError message={errors.confirmarSenha?.message} />
              </div>
            </div>

            <div className="space-y-4">
                <AnimatePresence>
                  {apiError && (
                    <motion.p
                      initial={{ opacity: 0, height: 0 }}
                      animate={{ opacity: 1, height: 'auto' }}
                      exit={{ opacity: 0, height: 0 }}
                      className="text-center text-sm text-red-600"
                    >
                      {apiError}
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
                  {isLoading ? 'Criando conta...' : 'Finalizar Cadastro'}
                </motion.button>
            </div>
        </form>

        <p className="mt-6 text-center text-sm text-slate-600">
          Já tem uma conta?{" "}
          <Link to="/login" className="font-medium text-brand-yellow hover:text-brand-yellow-darker">
            Faça login
          </Link>
        </p>
      </motion.div>
    </div>
  );
}

export default RegisterPage;
