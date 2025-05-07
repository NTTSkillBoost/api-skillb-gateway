# Spring Cloud Gateway Local com Alta Disponibilidade

## Como usar

1️⃣ Substitua `sua-imagem-spring-cloud-gateway` no `docker-compose.yml` pela sua imagem do Gateway.

2️⃣ Suba os serviços:

```bash
docker-compose up -d --scale api-gateway=3


Acesse pelo navegador ou ferramentas de teste:
http://localhost:81


==========
Testar escalabilidade
Para subir mais instâncias:
docker-compose up -d --scale api-gateway=5
```
Para parar tudo:
docker-compose down

=============================================
## Configurar o Nginx
1 - O NGINX distribui as requisições usando round robin.
2 - As instâncias podem ser escaladas rapidamente sem mudar a configuração do NGINX

---

## 🔎 Diagrama rápido

```plaintext
Client → NGINX (porta 80)
            ↓
    ┌───────────────┬───────────────┬───────────────┐
    │ api-gateway_1 │ api-gateway_2 │ api-gateway_3 │
    └───────────────┴───────────────┴───────────────┘