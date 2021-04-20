import {QuadraticFunction} from "./quadratic-function.js";
import {gradient, gradientGreatDescent, gradientConjugate} from "./gradient.js";
import {drawGradient, drawStepGradient, drawGreatGradient, drawConjugateGradient} from "./draw.js";

const EPS = 0.0001;
const plot = document.getElementById('plotly');
const dotMin = document.getElementById("dot-min");
const funcMin = document.getElementById("func-min");

const quad1 = new QuadraticFunction([[24, 6], [6, 14]], [-5, 10], -11,
    (x, y) => (24 * x * x) + (6 * x * y) + (14 * y * y) + (-5 * x) + (10 * y) - 11, "24x^2 + 6xy + 14y^2 - 5x + 10y - 11");

const quad2 = new QuadraticFunction([[64, 126], [126, 64]], [-10, 30], 13,
    (x, y) => (64 * x * x) + (126 * x * y) + (64 * y * y) + (-10 * x) + (30 * y) + 13, "64x^2 + 126xy + 64y^2 - 10x + 30y + 13");

const quad3 = new QuadraticFunction([[35, -69], [-69, 35]], [-34, 25], -24,
    (x, y) => (35 * x * x) + (-69 * x * y) + (35 * y * y) + (-34 * x) + (25 * y) - 24, "35x^2 - 69xy + 35y^2 - 34x + 25y - 24");

const current = {
    func: null,
    method: null,
}
const elemByName = {
    quad1, quad2, quad3, gradient, gradientGreatDescent, gradientConjugate
}
const elemToNode = new Map();
elemToNode.set(quad1, document.getElementById("quad1"));
elemToNode.set(quad2, document.getElementById("quad2"));
elemToNode.set(quad3, document.getElementById("quad3"));
elemToNode.set(gradient, document.getElementById("gradient"));
elemToNode.set(gradientGreatDescent, document.getElementById("gradientGreatDescent"));
elemToNode.set(gradientConjugate, document.getElementById("gradientConjugate"));

// CHOSE ELEMENTS
const methods = document.querySelectorAll(".methods li")
const functions = document.querySelectorAll(".functions li")
addHandlers(methods, "method")
addHandlers(functions, "func")

function addHandlers(nodes, element) {
    nodes.forEach((node) => {
        node.addEventListener("click", function () {
            if (current[element]) {
                elemToNode.get(current[element]).classList.remove("active")
            }
            current[element] = elemByName[node.id]
            node.classList.add("active")
            current.method && current.func && (plot.style.display = "block") && calculate();
        })
    })
}

function calculate() {
    const data = current.method([20, 20], current.func, EPS, -100, 100);
    const dataX = data.map(el => el[0]);
    const dataY = data.map(el => el[1]);

    drawGradient(dataX, dataY, current.func.func, current.func.funcStr);
    dotMin.innerText = data[data.length - 1]
    funcMin.innerText = current.func.func(...data[data.length - 1])

    plot.scrollIntoView({
        block: "center",
        behavior: "smooth",
    })
}

drawStepGradient();
drawGreatGradient();
drawConjugateGradient();
