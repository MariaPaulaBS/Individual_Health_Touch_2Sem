
import psutil
import time
import platform
import datetime
from mysql.connector import connect

# Conectando com o Workbench para fazer os selects
# Coloque suas credenciais do banco
conn = connect(
    host='localhost',
    user='root',
    password='181004Mp.',
    database='HealthTouch'
)

print("Bem Vindo à Aplicação Health Touch")

cursor = conn.cursor()

# Rodando o monitoramento
while True:
    uso_cpu = round(psutil.cpu_percent(interval=1), 2)
    uso_memoria = round(psutil.virtual_memory().percent, 2)
    data = datetime.datetime.now()

    query = '''
            insert into Monitoramento(porcentagem, dataHora, fkComponente, fkMaquina, fkPlanoEmpresa, fkTipoMaquina, fkEmpresaMaquina)
            VALUES (%s, %s, %s, %s, %s, %s, %s)
            '''

    insert = [
        uso_cpu, data, 1, 1, 1, 1, 1
    ]

    cursor.execute(query, insert)
    conn.commit()

    insert = [
        uso_memoria, data, 3, 1, 1, 1, 1
    ]

    cursor.execute(query, insert)
    conn.commit()

    time.sleep(5)
    print(f"Uso da CPU: {uso_cpu}%")
    print(f"Uso da Memória: {uso_memoria}%\r\n")

cursor.close()
