import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '10s', target: 10 },
        { duration: '20s', target: 20 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        'http_req_failed': ['rate<0.1'],
    }
};

export default function () {
    const url = 'http://localhost:9191/clients';

    const payload = JSON.stringify({
        name: 'Maria da Silva 3352',
        email: 'maria.silva@email.com'
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let res = http.post(url, payload, params);

    check(res, {
        'status é 200 ou 429': (r) => r.status === 200 || r.status === 201 || r.status === 429,
    });

    //let res = http.post('http://localhost:9191/clients');
    //check(res, {
    //    'status é 200 ou 429': (r) => r.status === 200 || r.status === 429,
    //});

    sleep(1);
}
