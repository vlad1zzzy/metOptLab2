const plot = document.getElementById('plotly');

export function drawGradient(x, y, func) {
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
        title: "Gradient",
        showlegend: false,
        hovermode: 'closest',
        bargap: 0,
    };

    Plotly.newPlot(plot, data, layout);
}