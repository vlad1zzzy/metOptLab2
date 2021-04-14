export function gradient(xk, quadFunc, epsilon, a, b) {
    let k = 0;
    const data = [xk]
    let lambda = quadFunc.findMin(xk, a, b, epsilon);
    let x;
    do {
        x = xk;
        xk = quadFunc.reducedAddVectors(x, quadFunc.findGradient(x, -1), lambda);
        data.push(xk);
        while (quadFunc.findFx(xk) > quadFunc.findFx(x)) {
            if (k > 1000) {
                return data;
            }
            lambda /= 2;
            xk = quadFunc.reducedAddVectors(x, quadFunc.findGradient(x, -1), lambda);
            data.push(xk);
            k++;
        }
        k++;
    } while (quadFunc.dfNormalize(xk) > epsilon && k < 1000);
    return data;
}

export function gradientGreatDescent(xk, quadFunc, epsilon, a, b) {
    return gradientConjugate(xk, quadFunc, epsilon, a, b, 1);
}
export function gradientConjugate(xk, quadFunc, epsilon, a, b, n) {
    let k = 0;
    let lambda, beta, g1;
    let x, p = quadFunc.findGradient(xk, -1);
    const data = [xk]
    do {
        k++;
        x = xk;
        lambda = quadFunc.findMin(x, a, b, epsilon);
        xk = quadFunc.reducedAddVectors(x, p, lambda);
        data.push(xk);
        g1 = quadFunc.dfNormalize(xk);
        if (k % n === 0) {
            p = quadFunc.findGradient(xk, -1);
        } else {
            let g = quadFunc.dfNormalize(x);
            beta = g1 * g1 / (g * g);
            p = quadFunc.reducedAddVectors(quadFunc.findGradient(xk, -1), p, beta);
        }
    } while (g1 > epsilon && k < 1000);
    return data;
}