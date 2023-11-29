import psutil
import time
import platform
import datetime
from mysql.connector import connect
import pymssql

# Conectando com o Workbench para fazer os selects
# Coloque suas credenciais do banco
conn = connect(
    host='localhost',
    user='root',
    password='181004Mp.',
    database='HealthTouch'
)
sql_server_connection = pymssql.connect(server='54.145.218.19', database='HealthTouch', user='sa', password='urubu100')

def insert_data(connection, query, values):
    cursor = sql_server_connection.cursor()
    cursor.execute(query, values)
    connection.commit()

print("Bem Vindo à Aplicação Health Touch")
cursor = conn.cursor()
i = 0

# Rodando o monitoramento
while i == 0:
    if True == True:
        uso_cpu = round(psutil.cpu_percent(interval=1), 2)
        uso_memoria = round(psutil.virtual_memory().percent, 2)
        data = datetime.datetime.now()

        query = '''
            insert into Monitoramento(porcentagem, dataHora, fkComponente, fkMaquina, fkPlanoEmpresa, fkTipoMaquina, fkEmpresaMaquina)
            VALUES (%s, %s, %s, %s, %s, %s, %s)
        '''

        insert_values_cpu = (
            uso_cpu, data, 1, 1, 1, 1, 1
        )

        insert_values_ram = (
            uso_memoria, data, 3, 1, 1, 1, 1
        )

        query_server = '''
            insert into Monitoramento(porcentagem, dataHora, fkComponente, fkMaquina, fkPlanoEmpresa, fkTipoMaquina, fkEmpresaMaquina)
            VALUES (%s, %s, %s, %s, %s, %s, %s)
        '''

        insert_data(sql_server_connection, query_server, insert_values_cpu)
        insert_data(sql_server_connection, query_server, insert_values_ram)

        cursor.execute(query, insert_values_cpu)
        cursor.execute(query, insert_values_ram)
        conn.commit()

        time.sleep(1)
        print(f"Uso da CPU: {uso_cpu}%")
        print(f"Uso da Memória: {uso_memoria}%\r\n")

    else:
        print("saiiiiii")
        i = 1
        cursor.close()
