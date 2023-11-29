package app

import com.github.britooo.looca.api.core.Looca
import javax.swing.JOptionPane
import Processo
import Maquina
import Colaborador
import API_Python
import Repositorio


    open class Main {
        companion object {
            @JvmStatic fun main(args: Array<String>) {

                    val repositorio = Repositorio ()
                    repositorio.iniciar()
                    val colaborador = Colaborador()
                    val locca = Looca()
                    val maquina = Maquina ()
                    val processo = Processo()
                    val apiPython = API_Python()


                    val processos = Processo()
                    val grupoProcesssos = locca.grupoDeProcessos

                    JOptionPane.showMessageDialog(null, """
        Bem Vindo a Health Touch!!!
        Para verificar se você está no Sistema da Health Touch iremos fazer um login.
    """.trimIndent())


                    colaborador.email = JOptionPane.showInputDialog("""
        Insira seu email:
    """.trimIndent())
                    colaborador.senha  = JOptionPane.showInputDialog("""
        Insira sua senha :
    """.trimIndent())


                    val vColaborador : Int? = repositorio.verificarColaborador(colaborador.email, colaborador.senha)

                    if (vColaborador != 0){
                        repositorio.buscarNome(colaborador.email, colaborador.senha, colaborador)
                        if (vColaborador != null){
                            JOptionPane.showMessageDialog(null, """
                Bem vindo ${colaborador.nome}!!!
                Você está dentro do Sistema da Health Touch.
            """.trimIndent())


                            while (true){

                                val opcao = JOptionPane.showInputDialog("""
                        Agora ${colaborador.nome },
                        Escolha uma das opções abaixo : 

                        1 - [Kotlin]: Capturar Processos API 
                        2 - [Kotlin]: Listar Processos
                        3 - [Kotlin]: Ver Total de Processos Capturados
                        4 - [Kotlin]: Ver Total de Threads Capturados
                        5 - [Kotlin]: Pesquisar Processo
                        6 - [Python]: Capturar com API cpu e ram
                        7 - [Python]: Parar de Capturar na API cpu e ram
                        8 - Sair


                    """.trimIndent()).toInt()

                                when(opcao){
                                    1 -> {
                                        val ip =  JOptionPane.showInputDialog("""
                            Qual é o IP da máquina que você quer capturar ?
                        """.trimIndent()).toInt()

                                        val vmaquina =   repositorio.validarMaquina(ip)

                                        if (vmaquina != null) {
                                            if (vmaquina != 0) {


                                                JOptionPane.showMessageDialog(
                                                    null, """
                                Você está capturando!!
                            """.trimIndent()
                                                )


                                                repositorio.buscaridMaquina(ip, processo)
                                                repositorio.buscarfkEmpresa(ip, processo)
                                                repositorio.buscarfkTipoMaquina(ip, processo)
                                                repositorio.buscarfkStatusMaquina(ip, processo)
                                                repositorio.buscarfkPlanoEmpresa(ip, maquina)

                                                repositorio.cadastrarProcesso(processo)



                                            }
                                        }

                                        else{
                                            JOptionPane.showMessageDialog(null, """
                                IP errado você não está capturando!!
                            """.trimIndent())
                                        }





                                    }
                                    2 -> {
                                        println( """
                            
                       Nome: ${processo.nome}
                       PID: ${processo.PID}
                       Uso de memória (GB): ${processo.uso_ram}
                       Uso de CPU (%): ${processo.uso_cpu}
                       Total de processos: ${grupoProcesssos.totalProcessos}
                       Total de threads: ${grupoProcesssos.totalThreads}
                        """.trimIndent()
                                        )
                                    }
                                    3-> {
                                        val vProcessos = repositorio.verificarProcesso()
                                        if (vProcessos != null){
                                            if (vProcessos != 0) {
                                                processos.total_processos = grupoProcesssos.totalProcessos
                                                JOptionPane.showMessageDialog(null, """
                                   Total de Processos Capturados é de:
                                   ${processos.total_processos}
                               """.trimIndent())
                                            }
                                        }
                                    }
                                    4-> {
                                        val vProcessos = repositorio.verificarProcesso()
                                        if (vProcessos != null){
                                            if (vProcessos != 0) {
                                                processos.total_threads = grupoProcesssos.totalThreads
                                                JOptionPane.showMessageDialog(null, """
                                   Total de Threads Capturados é de:
                                   ${processos.total_threads}
                               """.trimIndent())
                                            }
                                        }
                                    }
                                    5-> repositorio.pesquisarProcesso()
                                    6 ->{

                                        val ip =  JOptionPane.showInputDialog("""
                            Qual é o IP da máquina que você quer capturar ?
                        """.trimIndent()).toInt()

                                        val vmaquina =   repositorio.validarMaquina(ip)

                                        if (vmaquina != 0) {
                                            if (vmaquina != null) {


                                                JOptionPane.showMessageDialog(
                                                    null, """
                                Você está capturando!!
                            """.trimIndent()
                                                )


                                                repositorio.buscaridMaquina(ip, processo)
                                                repositorio.buscarfkEmpresa(ip, processo)
                                                repositorio.buscarfkTipoMaquina(ip, processo)
                                                repositorio.buscarfkStatusMaquina(ip, processo)
                                                repositorio.buscarfkPlanoEmpresa(ip, maquina)


                                                apiPython.sair = "True"
                                                println(apiPython.sair)
                                                apiPython.chamarApiPython(maquina, processo)




                                            }}
                                        else{
                                            JOptionPane.showMessageDialog(null, """
                                IP errado você não está capturando!!
                            """.trimIndent())
                                        }



                                    }
                                    7-> {
                                        val ip =  JOptionPane.showInputDialog("""
                            Qual é o IP da máquina que você quer parar de capturar ?
                        """.trimIndent()).toInt()
                                        val sair =  JOptionPane.showInputDialog("""
                                        Se você quiser parar digite exit
                                    """.trimIndent())


                                        if (sair == "exit"|| sair == "Exit"|| sair == "EXIT"){



                                            val vmaquina =   repositorio.validarMaquina(ip)

                                            if (vmaquina != 0) {
                                                if (vmaquina != null) {


                                                    repositorio.buscaridMaquina(ip, processo)
                                                    repositorio.buscarfkEmpresa(ip, processo)
                                                    repositorio.buscarfkTipoMaquina(ip, processo)
                                                    repositorio.buscarfkStatusMaquina(ip, processo)
                                                    repositorio.buscarfkPlanoEmpresa(ip, maquina)




                                                    apiPython.sair = "False"
                                                    apiPython.encerrarPython()
                                                    apiPython.chamarApiPython(maquina, processo)
                                                    println( apiPython.sair)
                                                    JOptionPane.showMessageDialog(null, """
                                            Saindo!!!
                                        """.trimIndent())



                                                }}
                                            else{
                                                JOptionPane.showMessageDialog(null, """
                                IP errado você não está capturando!!
                            """.trimIndent())
                                            }



                                        }
                                    }

                                    else -> break
                                }
                            }





                        }
                    }

                    else {
                        JOptionPane.showMessageDialog(null, """
            Tente Novamente!!!
            Você não está dentro do Sistema da Health Touch.
        """.trimIndent())
                    }
                }


            }
        }


