import {QuadraticFunction} from "./quadratic-function.js";
import {gradient, gradientGreatDescent, gradientConjugate} from "./gradient.js";
import {drawGradient} from "./draw.js";

const EPS = 0.0001;

const quad = new QuadraticFunction([[64, 126], [126, 64]], [-10, 30], 13,
    (x, y) => (64 * x * x) + (126 * x * y) + (64 * y * y) + (-10 * x) + (30 * y) + 13);

const quad2 = new QuadraticFunction([[24, 6], [6, 14]], [-5, 10], -11,
    (x, y) => (24 * x * x) + (6 * x * y) + (14 * y * y) + (-5 * x) + (10 * y) - 11);

const quad3 = new QuadraticFunction([[35, -69], [-69, 35]], [-34, 25], -24,
    (x, y) => (35 * x * x) + (-69 * x * y) + (35 * y * y) + (-34 * x) + (25 * y) - 24);

let currentFunction = quad2;
let currentMethod = gradientConjugate;

const data = currentMethod([100, 100], currentFunction, EPS, -100, 100);
console.log(data)
const dataX = data.map(el => el[0]);
const dataY = data.map(el => el[1]);

drawGradient(dataX, dataY, currentFunction.func)

