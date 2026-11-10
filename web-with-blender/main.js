/* document.addEventListener("scroll", () => {
    const scrollY = window.scrollY;
    const startTransition = 500; // Hauteur où commence le changement
    const maxHeight = document.documentElement.scrollHeight - window.innerHeight;

    if (scrollY >= startTransition) {
        // Calcul de la progression après la hauteur définie
        const progress = Math.min((scrollY - startTransition) / (maxHeight - startTransition), 1);
        const gradientPosition = 33 + progress * 67; // Entre 33% et 100%

        document.getElementById("transitionDiv").style.background = 
            `linear-gradient(to bottom, purple ${gradientPosition}%, black 100%)`;
    }
}); */

window.addEventListener('scroll', function () {
    const overlay = document.querySelector('.overlay');
    const iframe = document.querySelector('iframe');
    const scrollY = window.scrollY || window.pageYOffset;
    const windowHeight = window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;

    // Calculer la progression du défilement (0 à 1)
    const scrollProgress = scrollY / (documentHeight - windowHeight);

    // Effet pour l'overlay noir
    overlay.style.right = `${-100 + (scrollProgress * 100)}%`;

    // Effet pour l'iframe
    if (scrollProgress < 0.25) {
        // Début du défilement : iframe au centre
        iframe.style.left = '50%';
        iframe.style.transform = 'translate(-50%, -50%)';
    } else if (scrollProgress >= 0.25 && scrollProgress < 0.5) {
        // À 25% : iframe se déplace à gauche
        iframe.style.left = '25%';
        iframe.style.transform = 'translate(-50%, -50%)';
    } else if (scrollProgress >= 0.5 && scrollProgress < 0.75) {
        // À 50% : iframe se déplace à droite
        iframe.style.left = '75%';
        iframe.style.transform = 'translate(-50%, -50%)';
    } else {
        // À 75% : iframe revient au centre
        iframe.style.left = '50%'; 
        iframe.style.transform = 'translate(-50%, -50%)';
    }
});

const keys = document.querySelectorAll(".key");

function pressRandomKey() {
  const randomKey = keys[Math.floor(Math.random() * keys.length)];

  randomKey.style.animation = "pressDown 0.2s ease-in-out";

  randomKey.onanimationend = () => {
    randomKey.style.animation = "";
    setTimeout(pressRandomKey, 100 + Math.random() * 300);
  };
}

pressRandomKey();