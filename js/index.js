import {QuadraticFunction} from "./quadratic-function.js";
import {gradient, gradientGreatDescent, gradientConjugate} from "./gradient.js";
import {drawGradient, drawStepGradient, drawGreatGradient, drawConjugateGradient} from "./draw.js";

//const EPS = 0.00000001;
const plot = document.getElementById('plotly');
const dotMin = document.getElementById("dot-min");
const funcMin = document.getElementById("func-min");
const iterations = document.getElementById("iterations");

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

// CHOSE ACCURACY
const epsSlider = document.getElementById("eps");
const epsValue = document.getElementById("eps-value");
epsValue.innerHTML = epsSlider.value;
epsSlider.oninput = function () {
    epsValue.innerHTML = this.value;
}

// STEPS
const arrows = document.querySelector(".arrows");
const prev = arrows.querySelector(".prev");
const next = arrows.querySelector(".next");
let step;
prev.addEventListener("click", function () {
    let flag = calculate(--step)
    if (!flag) {
        next.classList.remove("disabled")
    }
    if (step === 1) {
        prev.classList.add("disabled")
    }
})
next.addEventListener("click", function () {
    let flag = calculate(++step)
    if (step > 0) {
        prev.classList.remove("disabled")
    }
    if (flag) {
        next.classList.add("disabled")
    }
})

// CALCULATE
const calc = document.getElementById("calculate");
calc.onclick = function () {
    step = 0;
    prev.classList.add("disabled")
    next.classList.remove("disabled")
    current.method && current.func && (plot.style.display = "block") && calculate();
}

function addHandlers(nodes, element) {
    nodes.forEach((node) => {
        node.addEventListener("click", function () {
            if (current[element]) {
                elemToNode.get(current[element]).classList.remove("active")
            }
            current[element] = elemByName[node.id]
            node.classList.add("active")
            //current.method && current.func && (plot.style.display = "block") && calculate();
        })
    })
}

function calculate(step = -2) {
    const data = current.method([20, 20], current.func, Math.pow(10, -epsValue.innerHTML), -100, 100);
    const dataX = data.map(el => el[0]).slice(0, step + 1);
    const dataY = data.map(el => el[1]).slice(0, step + 1);

    drawGradient(dataX, dataY, current.func.func, current.func.funcStr);
    dotMin.innerText = data[data.length - 1]
    funcMin.innerText = current.func.func(...data[data.length - 1])
    iterations.innerText = data.length;

    plot.scrollIntoView({
        block: "center",
        behavior: "smooth",
    })
    arrows.classList.remove("disabled")
    return step === data.length - 1;
}

drawStepGradient();
drawGreatGradient();
drawConjugateGradient();

function animate(x, y) {
    var frames = []
    var n = 100;
    for (var i = 0; i < n; i++) {
        frames[i] = {data: [{x: [], y: []}, {x: [], y: []}]}
        frames[i].data[1].x = x.slice(0, i+1);
        frames[i].data[1].y = y.slice(0, i+1);
    }

    var trace2 = {
        type: "scatter",
        mode: "lines",
        name: 'AAPL High',
        fill: 'tonexty',
        x: frames[5].data[1].x,
        y: frames[5].data[1].y,
        line: {color: 'grey'}
    }

    var trace1 = {
        type: "scatter",
        mode: "lines",
        name: 'AAPL Low',
        x: frames[5].data[0].x,
        y: frames[5].data[0].y,
        line: {color: 'lightgrey'}
    }

    var data = [trace1,trace2];

    var layout = {
        title: 'Multiple Trace Filled-Area Animation',
        xaxis: {
            range: [frames[99].data[0].x[0], frames[99].data[0].x[99]],
            showgrid: false
        },
        yaxis: {
            range: [120, 140],
            showgrid: false
        },
        legend: {
            orientation: 'h',
            x: 0.5,
            y: 1.2,
            xanchor: 'center'
        },
        updatemenus: [{
            x: 0.5,
            y: 0,
            yanchor: "top",
            xanchor: "center",
            showactive: false,
            direction: "left",
            type: "buttons",
            pad: {"t": 87, "r": 10},
            buttons: [{
                method: "animate",
                args: [null, {
                    fromcurrent: true,
                    transition: {
                        duration: 0,
                    },
                    frame: {
                        duration: 40,
                        redraw: false
                    }
                }],
                label: "Play"
            }, {
                method: "animate",
                args: [
                    [null],
                    {
                        mode: "immediate",
                        transition: {
                            duration: 0
                        },
                        frame: {
                            duration: 0,
                            redraw: false
                        }
                    }
                ],
                label: "Pause"
            }]
        }]
    };

    Plotly.newPlot('myDiv', data, layout).then(function() {
        Plotly.addFrames('myDiv', frames);
    });
}