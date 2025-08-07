
import React from 'react';

// A lógica de cálculo permanece a mesma, mas vamos ajustar o retorno
const calculatePasswordStrength = (password) => {
  let score = 0;
  if (!password) {
    return { label: '', color: 'bg-slate-200', width: '0%' };
  }
  if (password.length >= 8) score++;
  if (password.match(/[a-z]/)) score++;
  if (password.match(/[A-Z]/)) score++;
  if (password.match(/[0-9]/)) score++;
  if (password.match(/[^a-zA-Z0-9]/)) score++;

  switch (score) {
    case 1:
      return { label: 'Muito Fraca', color: 'bg-red-500', width: '20%' };
    case 2:
      return { label: 'Fraca', color: 'bg-orange-500', width: '40%' };
    case 3:
      return { label: 'Média', color: 'bg-yellow-500', width: '60%' };
    case 4:
      return { label: 'Forte', color: 'bg-teal-500', width: '80%' };
    case 5:
      return { label: 'Muito Forte', color: 'bg-green-500', width: '100%' };
    default:
      return { label: '', color: 'bg-slate-200', width: '0%' };
  }
};

const PasswordStrengthMeter = ({ password }) => {
  const { label, color, width } = calculatePasswordStrength(password);

  if (!password) return null;

  return (
    <div className="mt-2" aria-live="polite">
      <div className="w-full bg-slate-200 rounded-full h-1.5">
        <div
          className={`h-1.5 rounded-full transition-all duration-300 ease-in-out ${color}`}
          style={{ width: width }}
        ></div>
      </div>
      <p className="text-xs text-slate-500 mt-1 text-right font-medium">
        Força: <strong>{label}</strong>
      </p>
    </div>
  );
};

export default PasswordStrengthMeter;
