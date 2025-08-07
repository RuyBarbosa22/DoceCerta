/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'brand-yellow': '#FFD100', 
        'brand-yellow-darker': '#F2C100', 
    
      }
    },
  },
  plugins: [],
}