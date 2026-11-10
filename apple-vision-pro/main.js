document.addEventListener('DOMContentLoaded', () => {
    // 1. Initialisation des particules
    particlesJS('particles-js', {
        particles: {
            number: { value: 60, density: { enable: true, value_area: 800 } },
            color: { value: "#ffffff" },
            shape: { type: "circle" },
            opacity: { value: 0.5, random: true },
            size: { value: 3, random: true },
            line_linked: { 
                enable: true, 
                distance: 150, 
                color: "#ffffff", 
                opacity: 0.3, 
                width: 1 
            },
            move: { 
                enable: true, 
                speed: 2, 
                direction: "none", 
                random: true, 
                straight: false, 
                out_mode: "out" 
            }
        },
        interactivity: {
            events: {
                onhover: { enable: true, mode: "repulse" },
                onclick: { enable: true, mode: "push" }
            }
        }
    });

    // 2. Configuration Sketchfab
    let viewer;
    const iframe = document.getElementById('dmodel');
    const client = new Sketchfab.Viewer(iframe, {
        success: function(api) {
            viewer = api;
            api.start();
            api.setCameraLookAt([0, 0, 2], [0, 0, 0], 1);
        },
        error: function() {
            console.error("Erreur de chargement du modèle 3D");
        }
    });

    // 3. Effets de scroll
    let lastScroll = 0;
    let rotationY = 0;
    let targetY = 0;

    function animate() {
        // Lissage progressif
        rotationY += (targetY - rotationY) * 0.1;
        
        // Applique la rotation via l'API Sketchfab
        if(viewer) {
            viewer.setCameraLookAt(
                [Math.sin(rotationY) * 2, 0.5, Math.cos(rotationY) * 2],
                [0, 0, 0],
                0
            );
        }
        
        requestAnimationFrame(animate);
    }

    window.addEventListener('scroll', () => {
        const scrollY = window.scrollY;
        
        // Rotation progressive (1 tour complet = 1000px de scroll)
        targetY = scrollY * 0.00628; // 2π/1000
        
        // Parallax sur le conteneur
        const parallaxY = scrollY * 0.2;
        document.querySelector('.model-wrapper').style.transform = 
            `translate(-50%, calc(-50% + ${parallaxY}px))`;
        
        lastScroll = scrollY;
    });

    // 4. Animations d'apparition
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if(entry.isIntersecting) {
                entry.target.classList.add('visible');
            }
        });
    }, { threshold: 0.1 });

    document.querySelectorAll('.header, .section').forEach(el => {
        observer.observe(el);
    });

    // Démarrer l'animation
    animate();
});