// Effet parallax amélioré
window.addEventListener('scroll', () => {
    const iframe = document.querySelector('.model-3d');
    const scrollY = window.scrollY;
    iframe.style.transform = `translateY(${scrollY * 0.5}px) scale(${1 + scrollY * 0.001})`;
});
