import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
object Conexao {
    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSource = BasicDataSource()
                dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
                dataSource.url = "jdbc:mysql://localhost:3306/HealthTouch"
                dataSource.username = "root"
                dataSource.password = "181004Mp."
                val novoJdbcTemplate = JdbcTemplate(dataSource)
                field = novoJdbcTemplate
            }
            return field
        }

    fun criarTabelas() {

        jdbcTemplate!!.execute(
            """
                 CREATE TABLE IF NOT EXISTS processo (
            idProcesso int  auto_increment,
            nome varchar(45),
            PID int,
            uso_cpu double,
            uso_ram double, 
            total_processos int,
            total_threads int, 
            dtHoraInsercao  datetime,
            fkMaquina int, 
            constraint fk_maquina_processo foreign key(fkMaquina) references Maquina(idMaquina),
            fkEmpresa int, 
            constraint fk_empresa_processo foreign key(fkEmpresa) references Empresa(idEmpresa),
            fkTipoMaquina int, 
            constraint fk_tipo_maquina_processo foreign key(fkTipoMaquina) references TipoMaquina(idTipoMaquina),
            fkStatusMaquina int, 
            constraint fk_status_maquina_processo foreign key(fkStatusMaquina) references StatusMaquina(idStatusMaquina),
            constraint pk_composta_processo primary key (idProcesso, fkMaquina,  fkEmpresa, fkTipoMaquina, fkStatusMaquina) 
        )
            """
        )

        jdbcTemplate!!.execute(
            """
                 CREATE TABLE IF NOT EXISTS Colaborador (
           create table Colaborador (
           idColaborador int auto_increment,
           nome varchar(45),
           email varchar(45),
           senha varchar(45),
           CPF char(14),
           fkEmpresa int, 
           constraint fk_empresa_colabrador foreign key(fkEmpresa) references Empresa(idEmpresa),
           fkStatus int, 
           constraint fk_status_colaborador foreign key(fkStatus) references statusColaborador(idStatusColaborador),
           fkNivelAcesso int, 
           constraint fk_nivel_acesso_colaborador foreign key(fkNivelAcesso) references NivelAcesso(idNivelAcesso),
           constraint pk_composta_colaborador primary key (idColaborador, fkEmpresa, fkStatus, fkNivelAcesso)
         );
        )
            """
        )


        jdbcTemplate!!.execute(
            """
                 CREATE TABLE IF NOT EXISTS Maquina (
            idMaquina int auto_increment,
            SO varchar(45),
            IP char(9),
            fkEmpresa int,
            constraint fk_empresa_maquina foreign key(fkEmpresa) references Empresa(idEmpresa),
            fkLocal int,
            constraint fk_local_sala_maquina  foreign key(fkLocal) references LocalSala(idLocalSala),
            fkPlanoEmpresa int,
            constraint fk_plano_empresa_maquina  foreign key(fkPlanoEmpresa) references Plano(idPlano),
            fkStatusMaquina int,
            constraint fk_status_maquina  foreign key(fkStatusMaquina) references statusMaquina(idStatusMaquina),
            fkTipoMaquina int,
            constraint fk_tipo_maquina  foreign key(fkTipoMaquina) references TipoMaquina(idTipoMaquina),
            constraint pk_composta_maquina primary key (idMaquina, fkEmpresa, fkPlanoEmpresa, fkStatusMaquina, fkTipoMaquina)
        );
        
            """
        )





    }


    var bdInterServer: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSoruceServer = BasicDataSource()
                dataSoruceServer.url = "jdbc:sqlserver://54.145.218.19;databaseName=HealthTouch;encrypt=false";
                dataSoruceServer.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                dataSoruceServer.username = "sa"
                dataSoruceServer.password = "urubu100"
                bdInterServer = JdbcTemplate(dataSoruceServer)
            }
            return field
        }
}
