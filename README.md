2. Command via Messaging
   🧠 Conceito:
   Outro serviço (ex: API Gateway ou outro produtor) envia uma mensagem com intenção (comando) via RabbitMQ (ex: CriarClienteCommand), e o cliente-service escuta e executa o comando.

🧱 Fluxo Command via Messaging:

[API Gateway] → envia comando via RabbitMQ
                |
                ▼
RabbitMQ (mensagem: CriarClienteCommand)
                |
                ▼
[cliente-service] → processa e salva no banco

✅ Exemplo de uso:

Gateway publica:
json
{
    "action": "create",
    "cliente": {
        "nome": "Maria",
        "email": "maria@email.com"
    }
}

cliente-service escuta a fila criar-cliente-command
Processa a mensagem e salva no banco

✅ Vantagens:
Permite desacoplar completamente o serviço produtor do consumidor
Útil quando o serviço consumidor pode estar temporariamente offline
Boa alternativa para sistemas eventualmente consistentes com comandos assíncronos

==
1. Event-Carried State Transfer (ECST)
   🧠 Conceito:
   O serviço que detém os dados (ex: cliente-service) realiza a ação via HTTP (ex: cadastro) e emite um evento com o estado completo para que outros serviços sincronizem uma cópia local do dado.

🧱 Fluxo ECST:

[API Gateway]
    |
    ▼
[cliente-service] → salva no banco
    |
    ▼
RabbitMQ (evento: ClienteCriado)
    |
    ▼
[marketing-service] → atualiza seu banco local
✅ Exemplo de uso:
cliente-service recebe POST /clientes
Salva no seu banco
Emite evento:

json
{
    "id": 1,
    "nome": "Maria",
    "email": "maria@email.com"
}
marketing-service escuta o evento e replica em seu banco

✅ Vantagens:
Cada serviço mantém seu próprio banco (isolamento)
Sem acoplamento entre serviços
Ideal para consistência eventual e leitura local

# api-gateway


====================

ab.exe -n 20 -c 5 -p data.json -T application/json -v 4 http://localhost:9191/clients

Olhar: https://chatgpt.com/c/68154eb6-af64-8010-afae-2187c50ec779

===
🔎 Como vão ficar suas rotas no Gateway?
URL externa	                        Internamente redireciona para
/clients/**	                        /bff/clients/**
/orders/**	                        /bff/orders/**
/activities/**	                    /bff/activities/**
/activity-employees/**	            /bff/activity-employees/**
/employee-knowledge-advisors/** 	/bff/employee-knowledge-advisors/**
/courses/**	                        /bff/courses/**
/employees/**	                    /bff/employees/**
/employee-roles/**	                /bff/employee-roles/**
/enrollments/**	                    /bff/enrollments/**

# api-skillb-gateway
