let size = 400, x = new Array(size), y = new Array(size), z = new Array(size);

for(let i = 0; i < size; i++) {
    x[i] = 0.1 * i - 13;
    y[i] = 0.1 * i - 13;
    z[i] = new Array(size);
}

for(let i = 0; i < size; i++) {
    for(let j = 0; j < size; j++) {
        z[j][i] = 2 * x[i] * x[i] + 3 * y[j] * y[j] + 5 * x[i] - 4 * y[j];
    }
}

let trace1 = {
    x: [24.575424232048235, 2.2634808835458315, -0.3538307718640712,
        -1.12807853907119, -1.2189019906026903, -1.245769197804767,
        -1.2489182842760236, -1.24985085474682, -1.2499576796702325],
    y: [-10.995178167158642, 4.117744215067361, 0.2615062886906885,
        0.7865653536976266, 0.6525904262243737, 0.6708322337830619,
        0.6661814381019975, 0.6668089271284128, 0.666656086565874],
    mode: 'line',
    marker: {
        color: 'rgb(219, 64, 82)',
        size: 3
    }
};

let trace2 = {
    z: z,
    x: x,
    y: y,
    type: 'contour',
    colorscale: 'Jet',
    contours: {
        coloring: 'lines',
        start: 0,
        end: 1000,
        size: 50
    }
}

let data = [ trace1, trace2 ];

let layout = {
    title: "Gradient descent",
    showlegend: false,
    hovermode: 'closest',
    bargap: 0,
};

Plotly.newPlot('plotly', data, layout);
