Você pode usar o Dashboard Oficial do K6 no Grafana:

ID: 2587
Link: https://grafana.com/grafana/dashboards/2587-k6-load-testing-results/

Acesse o Grafana:
http://localhost:3000

Usuário: admin
Senha: admin123

1 - No menu lateral esquerdo, clique em Dashboards → New → Import.
2 - Clique em Upload JSON file ou cole o conteúdo do arquivo grafana-dashboard.json no campo.
3 - Em InfluxDB datasource, selecione a fonte de dados InfluxDB (se você já configurou como ensinei no README).
4 - Clique em Import.

Pronto ✅!
O dashboard será criado com todos os gráficos (tempo de resposta, total de requisições, status 200 e 429 etc.).]

🧠 Importante:
Se a data source (InfluxDB) ainda não estiver configurada:
1 - Vá em Configuration (ícone de engrenagem no menu lateral) → Data Sources → Add data source.
2 - Escolha InfluxDB.
3 -Configure:
    - URL: http://influxdb:8086
    - Database: k6
    - User: admin
    - Password: admin123
4- Salve.
Aí pode voltar ao passo de importar o dashboard.