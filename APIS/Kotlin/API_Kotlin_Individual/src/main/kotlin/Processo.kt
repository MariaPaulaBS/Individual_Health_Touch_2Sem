import java.math.BigDecimal
import java.time.LocalDateTime

class Processo {

    var idProcesso : Int = 0
    var PID : Int = 0
    var nome : String = ""
    var uso_cpu :BigDecimal= BigDecimal(0)
    var uso_ram :BigDecimal= BigDecimal(0)
    var total_processos : Int = 0
    var total_threads : Int = 0
    var dtHoraInsercao : String = ""
    var fkMaquina: Int = 0
    var fkEmpresa: Int = 0
    var fkStatus: Int = 0
    var fkTipoMaquina: Int = 0

}