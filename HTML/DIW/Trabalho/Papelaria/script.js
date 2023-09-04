let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    let dots = document.getElementsByClassName("dot");
    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";     
    dots[slideIndex - 1].className += " active";
}

function buildSlideshow(arr) {
    let divs = document.querySelectorAll('.mySlides.fade');

    for(let i = 0; i < 5; i++) {
        let img = document.createElement('img');
        img.style.width = '100%';
        img.src = arr[i].image;
        divs[i].append(img);
        divs[i].addEventListener('click', () => {
            window.open(`detalhes/?id=${arr[i].id}`, '_blank');
        });
    }
}

function buildDestaques(arr) {
    for(let i = 5; i < 8; i++) {
        let h3 = document.querySelector(`#destaques-${i - 4} h3`),
            h4 = document.querySelector(`#destaques-${i - 4} h4`),
            img = document.querySelector(`#destaques-${i - 4} img`),
            destaque = document.querySelector(`#destaques-${i - 4}`);

        h3.innerHTML = arr[i].title;
        h4.innerHTML = "R$ " + arr[i].price;
        img.src = arr[i].image;
        destaque.addEventListener('click', () => {
            window.open(`detalhes/?id=${arr[i].id}`, '_blank');
        });
    }
}

function buildMostViewed(arr) {
    for(let i = 8; i < 12; i++) {
        let img = document.querySelector(`#produto-${i - 7} img`),
            h4 = document.querySelector(`#produto-${i - 7} h4`),
            p = document.querySelector(`#produto-${i - 7} p.product-price`),
            produto = document.querySelector(`#produto-${i - 7}`);

        img.src = arr[i].image;
        h4.innerHTML = arr[i].title;
        p.innerHTML = "R$ " + arr[i].price;
        produto.addEventListener('click', () => {
            window.open(`detalhes/?id=${arr[i].id}`, '_blank');
        });
    }
}

function buildForYou(arr) {
    for(let i = 12; i < 20; i++) {
        let img = document.querySelector(`#escolhidos-${i - 11} img`),
            h4 = document.querySelector(`#escolhidos-${i - 11} h4`),
            p = document.querySelector(`#escolhidos-${i - 11} p.product-price`),
            escolhido = document.querySelector(`#escolhidos-${i - 11}`);

        img.src = arr[i].image;
        h4.innerHTML = arr[i].title;
        p.innerHTML = "R$ " + arr[i].price;
        escolhido.addEventListener('click', () => {
            window.open(`detalhes/?id=${arr[i].id}`, '_blank');
        });
    }
}

const navbar = document.querySelector(".navbar");
const menuButton = document.querySelector(".menu-button");

menuButton.addEventListener('click', () => {
    navbar.classList.toggle("show-menu");
});

fetch('https://fakestoreapi.com/products')
    .then(res=>res.json())
    .then(json=>{
        buildSlideshow(json);
        buildDestaques(json);
        buildMostViewed(json);
        buildForYou(json);
    });

document.querySelector('#form').addEventListener('submit', evt => {
    evt.preventDefault();
    let text = document.querySelector('#pesquisar');
    window.open(`pesquisa/?text=${text.value}`, '_blank');
});