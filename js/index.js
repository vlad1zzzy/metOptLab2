const EPS = 0.01;
const plot = document.getElementById('plotly');

class QuadraticFunction {
    constructor(a, b, c, func) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.func = func;
    }

    findFdkX(k, x) {
        let ans = this.reducedMultiplyVectors(this.a[k], x);
        ans += this.a[k][k] * x[k];
        ans += this.b[k];
        return ans;
    }

    findFx(x) {
        let ans = 0;
        for (let i = 0; i < this.a.length; i++) {
            for (let j = i; j < this.a.length; j++) {
                ans += this.a[i][j] * x[i] * x[j];
            }
        }
        ans += this.reducedMultiplyVectors(this.b, x);
        ans += this.c;
        return ans;
    }

    findG(x, lambda) {
        const newX = new Array(this.a.length);
        for (let i = 0; i < this.a.length; i++) {
            newX[i] = x[i] - lambda * this.findFdkX(i, x);
        }
        return this.findFx(newX);
    }

    findGradient(x, k) {
        const ans = new Array(this.a.length);
        for (let i = 0; i < this.a.length; i++) {
            ans[i] = k * this.findFdkX(i, x);
        }
        return ans;
    }

    dfNormalize(x) {
        const gradient = this.findGradient(x, 1);
        return Math.sqrt(this.reducedMultiplyVectors(gradient, gradient));
    }

    reducedAddVectors(x1, x2, k2) {
        const ans = new Array(x1.length);
        for (let i = 0; i < x1.length; i++) {
            ans[i] = x1[i] + k2 * x2[i];
        }
        return ans;
    }

    reducedMultiplyVectors(x1, x2) {
        let ans = 0;
        for (let i = 0; i < x1.length; i++) {
            ans += x1[i] * x2[i];
        }
        return ans;
    }

    findMin(a, b, eps, x) {
        let s = 0.00000001;
        let x1, x2, f1, f2;
        if (s > eps) {
            s = eps;
        }
        do {
            x1 = (a + b - s) / 2;
            x2 = (a + b + s) / 2;
            f1 = this.findG(x, x1);
            f2 = this.findG(x, x2);
            if (f1 <= f2) {
                b = x2;
            } else {
                a = x1;
            }
        } while ((b - a) / 2 >= eps);
        return (a + b) / 2;
    }
}

const quad = new QuadraticFunction([[4, 0], [0, 1]], [4, -6], 10,
    (x, y) => (4 * x * x) + (4 * x) + (y * y) + (-6 * y) + 10);

const data = gradientConjugate([10, 10], quad, EPS, -10, 10, 2);
const dataX = data.map(el => el[0]);
const dataY = data.map(el => el[1]);

drawGradient(dataX, dataY, quad.func)

function gradient(xk, quadFunc, epsilon, a, b) {
    let lambda = quadFunc.findMin(a, b, epsilon, x);
    let x;
    do {
        x = quadFunc.reducedAddVectors(xk, quadFunc.findGradient(xk, -1), lambda);
        if (quadFunc.findFx(xk) < quadFunc.findFx(x)) {
            xk = x;
        } else {
            lambda /= 2;
        }
    } while (quadFunc.dfNormalize(xk) > epsilon);
    System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
        .collect(Collectors.joining(", ", "ANSWER : f( ", " ) = ")) + quadFunc.findFx(xk));
    return quadFunc.findFx(xk);
}

function gradientGreatDescent(xk, quadFunc, epsilon, a, b) {
    return gradientConjugate(xk, quadFunc, epsilon, a, b, 1);
}

function gradientConjugate(xk, quadFunc, epsilon, a, b, n) {
    let k = 0, q = 0, lambda, beta, g1, x, p = quadFunc.findGradient(xk, -1);
    const data = [xk]
    do {
        k++;
        x = [...xk];
        lambda = quadFunc.findMin(a, b, epsilon, x);
        xk = quadFunc.reducedAddVectors(x, p, lambda);
        g1 = quadFunc.dfNormalize(xk);

        data.push(xk);
        if (k === n) {
            k = 0;
            p = quadFunc.findGradient(xk, -1);
        } else {
            let g = quadFunc.dfNormalize(x);
            beta = g1 * g1 / g / g;
            p = quadFunc.reducedAddVectors(quadFunc.findGradient(xk, -1), p, beta);
        }
        q++;
    } while (g1 > epsilon);
    return data;
}


function drawGradient(x, y, func) {
    const xAns = Math.round(x[x.length - 1]);
    const yAns = Math.round(y[y.length - 1]);
    const size = 300, x_lvl = new Array(size), y_lvl = new Array(size), z_lvl = new Array(size);
    for (let i = 0; i < size; i++) {
        x_lvl[i] = 0.1 * i - 10 + xAns;
        y_lvl[i] = 0.1 * i - 10 + yAns;
        z_lvl[i] = new Array(size);
    }

    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            z_lvl[j][i] = func(x_lvl[i], y_lvl[j]);
        }
    }

    let trace1 = {
        x: x,
        y: y,
        mode: 'line',
        marker: {
            color: 'rgb(219, 64, 82)',
            size: 3
        }
    };

    let trace2 = {
        x: x_lvl,
        y: y_lvl,
        z: z_lvl,
        type: 'contour',
        colorscale: 'Jet',
        contours: {
            coloring: 'lines',
            start: 0,
            end: 100,
            size: 5
        }
    }

    let data = [trace1, trace2];

    let layout = {
        title: "Gradient descent",
        showlegend: false,
        hovermode: 'closest',
        bargap: 0,
    };

    Plotly.newPlot(plot, data, layout);
}