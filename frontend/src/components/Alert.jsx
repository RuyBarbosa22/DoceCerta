import React from 'react';

function Alert({ type, message }) {
  if (!message) return null;

  const baseClasses = "p-4 rounded-md text-sm";
  const typeClasses = {
    success: "bg-green-100 text-green-800",
    error: "bg-red-100 text-red-800",
  };

  return (
    <div className={`${baseClasses} ${typeClasses[type] || typeClasses.error}`} role="alert">
      {message}
    </div>
  );
}

export default Alert;