import {QuadraticFunction} from "./quadratic-function.js";
import {gradient, gradientGreatDescent, gradientConjugate} from "./gradient.js";
import {drawGradient, drawStepGradient, drawGreatGradient, drawConjugateGradient} from "./draw.js";

const EPS = 0.0001;

const quad1 = new QuadraticFunction([[24, 6], [6, 14]], [-5, 10], -11,
    (x, y) => (24 * x * x) + (6 * x * y) + (14 * y * y) + (-5 * x) + (10 * y) - 11);

const quad2 = new QuadraticFunction([[64, 126], [126, 64]], [-10, 30], 13,
    (x, y) => (64 * x * x) + (126 * x * y) + (64 * y * y) + (-10 * x) + (30 * y) + 13);

const quad3 = new QuadraticFunction([[35, -69], [-69, 35]], [-34, 25], -24,
    (x, y) => (35 * x * x) + (-69 * x * y) + (35 * y * y) + (-34 * x) + (25 * y) - 24);

const functions = {
    quad1, quad2, quad3
}

const methods = {
    gradient, gradientGreatDescent, gradientConjugate
}

let currentFunction = quad3;
let currentMethod = gradientConjugate;

/*// CHOSE METHODS
const methods = document.querySelectorAll(".methods li")
methods.forEach((method) => {
    method.addEventListener("click", function (e) {
        document.getElementById(chosenFunctionName).classList.remove("active")
        currentMethod = e.target.id
        chosenFunction = functions[chosenFunctionName]
        method.classList.add("active")

        if (method.id !== "parabola") {
            removeDataParabola()
            arrows.classList.add("disabled")
        }

        while (dataTable.firstChild) {
            dataTable.removeChild(dataTable.firstChild)
            stats.length = 0
        }
        method.scrollIntoView({
            block: "center",
            behavior: "smooth",
        })
    })
})*/

const data = currentMethod([20, 20], currentFunction, EPS, -100, 100);
const dataX = data.map(el => el[0]);
const dataY = data.map(el => el[1]);

drawGradient(dataX, dataY, currentFunction.func);

drawStepGradient();
drawGreatGradient();
drawConjugateGradient();
