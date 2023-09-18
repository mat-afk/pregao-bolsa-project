package database;

import ativos.*;
import entities.Corretora;
import entities.Empresa;
import entities.Investidor;
import estruturasdedados.Fila;
import estruturasdedados.Pilha;
import ordens.Ordem;
import ordens.OrdemCompra;
import ordens.OrdemVenda;

import java.io.*;
import java.time.LocalDateTime;

public class DatabaseManager {

    public static void gravarAcao(Ativo acao) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter("ativos.txt", true))) {
            String idFormat = String.format("%-5s", acao.getId());
            String codigoFormat = String.format("%-10s", acao.getCodigo());
            String nomeEmpresaFormat = String.format("%-30s", acao.getEmpresa().getNome());
            String cotacaoFormat = String.format("%-15s", acao.getCotacao());
            String tipoFormat = String.format("%-15s", acao.getTipo());

            arquivo.println(idFormat + codigoFormat + nomeEmpresaFormat + cotacaoFormat + tipoFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gravarEmpresa(Empresa empresa) {

        try (PrintWriter arquivo = new PrintWriter(new FileWriter("empresas.txt", true))) {
            String idFormat = String.format("%-5s", empresa.getId());
            String nomeFormat = String.format("%-30s", empresa.getNome());
            String simboloFormat = String.format("%-10s", empresa.getSimbolo());
            String capitalizacaoFormat = String.format("%-15s", empresa.getCapitalizacao());
            String setorFormat = String.format("%-20s", empresa.getSetor());

            arquivo.println(idFormat + nomeFormat + simboloFormat + capitalizacaoFormat + setorFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gravarInvestidor(Investidor investidor) {

        try (PrintWriter arquivo = new PrintWriter(new FileWriter("investidores.txt", true))) {
            String idFormat = String.format("%-5s", investidor.getId());
            String nomeFormat = String.format("%-30s", investidor.getNome());
            String cpfFormat = String.format("%-20s", investidor.getCpf());
            String saldoFormat = String.format("%-15s", investidor.getSaldo());
            String corretoraFormat = String.format("%-30s", investidor.getCorretora().getNome());

            arquivo.println(idFormat + nomeFormat + cpfFormat + saldoFormat + corretoraFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gravarCorretora(Corretora corretora) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter("corretoras.txt", true))) {
            String idFormat = String.format("%-5s", corretora.getId());
            String nomeFormat = String.format("%-30s", corretora.getNome());
            String cnpjFormat = String.format("%-20s", corretora.getCnpj());

            arquivo.println(idFormat + nomeFormat + cnpjFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gravarRegistro(Registro registro) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter("registros.txt", true))) {
            String dataFormat = String.format("%-30s", registro.getData().toString());
            String ordemFormat = String.format("%-15s", registro.getOrdem().getTipo());
            String ativoFormat = String.format("%-15s", registro.getOrdem().getAtivo().getCodigo());
            String precoFormat = String.format("%-15s", registro.getPreco());
            String volumeFormat = String.format("%-10s", registro.getVolume());

            arquivo.println(dataFormat + precoFormat + volumeFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gravarOrdem(Ordem ordem) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter("ordens.txt", true))) {
            String codigoAtivoFormat = String.format("%-10s", ordem.getAtivo().getCodigo());
            String nomeInvestidorFormat = String.format("%-30s", ordem.getInvestidor().getNome());
            String precoFormat = String.format("%-15s", ordem.getPreco());
            String quantidadeFormat = String.format("%-10s", ordem.getQuantidade());
            String dataEmissaoFormat = String.format("%-30s", ordem.getDataEmissao().toString());
            String tipoFormat = String.format("%-15s", ordem.getTipo());

            arquivo.println(codigoAtivoFormat + nomeInvestidorFormat + precoFormat +
                    quantidadeFormat + dataEmissaoFormat + tipoFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Ativo lerAcao(int id) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("ativos.txt"))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                String idStr = linha.substring(0, 5).trim();
                String codigo = linha.substring(5, 15).trim();
                String nomeEmpresa = linha.substring(15, 45).trim();
                String cotacaoStr = linha.substring(45, 60).trim();
                String tipoStr = linha.substring(60, 75).trim();

                int acaoId = Integer.parseInt(idStr);
                double cotacao = Double.parseDouble(cotacaoStr);
                Empresa empresa = lerEmpresaPorNome(nomeEmpresa);

                if (acaoId == id) {
                    if(tipoStr.equals("Ordinaria")) {
                        return new Ordinaria(acaoId, codigo, empresa, cotacao);
                    } else if(tipoStr.equals("Preferencial")) {
                        return new Preferencial(acaoId, codigo, empresa, cotacao);
                    } else {
                        return new FII(acaoId, codigo, empresa, cotacao);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Ativo lerAcaoPorCodigo(String codigo) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("ativos.txt"))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                String idStr = linha.substring(0, 5).trim();
                String acaoCodigo = linha.substring(5, 15).trim();
                String nomeEmpresa = linha.substring(15, 45).trim();
                String cotacaoStr = linha.substring(45, 60).trim();
                String tipoStr = linha.substring(60, 75).trim();

                int id = Integer.parseInt(idStr);
                double cotacao = Double.parseDouble(cotacaoStr);
                Empresa empresa = lerEmpresaPorNome(nomeEmpresa);

                if (acaoCodigo.equals(codigo)) {
                    if(tipoStr.equals("Ordinaria")) {
                        return new Ordinaria(id, codigo, empresa, cotacao);
                    } else if(tipoStr.equals("Preferencial")) {
                        return new Preferencial(id, codigo, empresa, cotacao);
                    } else {
                        return new FII(id, codigo, empresa, cotacao);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Empresa lerEmpresa(int id) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("empresas.txt"))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {

                String idStr = linha.substring(0, 5).trim();
                int acaoId = Integer.parseInt(idStr);

                if(acaoId == id) {
                    String nome = linha.substring(5, 35).trim();
                    String simbolo = linha.substring(35, 45).trim();
                    String capitalizacaoStr = linha.substring(45, 60).trim();
                    String setor = linha.substring(60, 80).trim();
                    double capitalizacao = Double.parseDouble(capitalizacaoStr);

                    return new Empresa(id, nome, simbolo, capitalizacao, setor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Empresa lerEmpresaPorNome(String nome) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("empresas.txt"))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                String empresaNome = linha.substring(5, 35).trim();

                if(empresaNome.equals(nome)) {
                    String idStr = linha.substring(0, 5).trim();
                    String simbolo = linha.substring(35, 45).trim();
                    String capitalizacaoStr = linha.substring(45, 60).trim();
                    String setor = linha.substring(60, 80).trim();

                    int id = Integer.parseInt(idStr);
                    double capitalizacao = Double.parseDouble(capitalizacaoStr);

                    return new Empresa(id, nome, simbolo, capitalizacao, setor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Investidor lerInvestidor(int id) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("investidores.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String idStr = linha.substring(0, 5).trim();
                int investidorId = Integer.parseInt(idStr);

                if (investidorId == id) {
                    String nome = linha.substring(5, 35).trim();
                    String cpf = linha.substring(35, 55).trim();
                    String saldoStr = linha.substring(55, 70).trim();
                    double saldo = Double.parseDouble(saldoStr);
                    String nomeCorretora = linha.substring(70).trim();

                    Corretora corretora = lerCorretoraPorNome(nomeCorretora);

                    return new Investidor(id, nome, cpf, saldo, corretora);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Investidor lerInvestidorPorNome(String nome) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("investidores.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String investidorNome = linha.substring(5, 35).trim();

                if (investidorNome.equals(nome)) {
                    String idStr = linha.substring(0, 5).trim();
                    int id = Integer.parseInt(idStr);
                    String cpf = linha.substring(35, 55).trim();
                    String saldoStr = linha.substring(55, 70).trim();
                    double saldo = Double.parseDouble(saldoStr);
                    String nomeCorretora = linha.substring(70).trim();

                    Corretora corretora = lerCorretoraPorNome(nomeCorretora);

                    return new Investidor(id, nome, cpf, saldo, corretora);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Corretora lerCorretoraPorId(int id) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("corretoras.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String idStr = linha.substring(0, 5).trim();
                int corretoraId = Integer.parseInt(idStr);

                if (corretoraId == id) {
                    String nome = linha.substring(5, 35).trim();
                    String cnpj = linha.substring(35, 55).trim();

                    return new Corretora(id, nome, cnpj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Corretora lerCorretoraPorNome(String nome) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader("corretoras.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String corretoraNome = linha.substring(5, 35).trim();

                if (corretoraNome.equals(nome)) {
                    String idStr = linha.substring(0, 5).trim();
                    int id = Integer.parseInt(idStr);
                    String cnpj = linha.substring(35, 55).trim();

                    return new Corretora(id, nome, cnpj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Pilha<Registro> lerRegistrosPorAtivo(String codigoAtivo) {
        Pilha<Registro> registros = new Pilha<>();

        try (BufferedReader arquivo = new BufferedReader(new FileReader("registros.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String dataStr = linha.substring(0, 30).trim();
                String ordemTipoStr = linha.substring(30, 45).trim();
                String ativoCodigoStr = linha.substring(45, 60).trim();
                String precoStr = linha.substring(60, 75).trim();
                String volumeStr = linha.substring(75).trim();

                if (ativoCodigoStr.equals(codigoAtivo)) {
                    LocalDateTime data = LocalDateTime.parse(dataStr);
                    double preco = Double.parseDouble(precoStr);
                    int volume = Integer.parseInt(volumeStr);
                    Ordem ordem = lerOrdemPorTipo(ordemTipoStr).peek();

                    Registro registro = new Registro(data, ordem, preco, volume);
                    registros.push(registro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return registros;
    }

    public static Fila<Ordem> lerOrdemPorTipo(String tipoDesejado) {
        Fila<Ordem> ordens = new Fila<>();

        try (BufferedReader arquivo = new BufferedReader(new FileReader("ordens.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String codigoAtivo = linha.substring(0, 10).trim();
                String nomeInvestidor = linha.substring(10, 40).trim();
                String precoStr = linha.substring(40, 55).trim();
                double preco = Double.parseDouble(precoStr);
                String quantidadeStr = linha.substring(55, 65).trim();
                int quantidade = Integer.parseInt(quantidadeStr);
                String dataEmissaoStr = linha.substring(65, 95).trim();
                LocalDateTime dataEmissao = LocalDateTime.parse(dataEmissaoStr);

                Ativo ativo = lerAcaoPorCodigo(codigoAtivo);
                Investidor investidor = lerInvestidorPorNome(nomeInvestidor);

                Ordem ordem = null;
                if(tipoDesejado.equals("Compra")) {
                    ordem = new OrdemCompra(ativo, investidor, preco, quantidade, dataEmissao);
                } else if(tipoDesejado.equals("Venda")) {
                    ordem = new OrdemVenda(ativo, investidor, preco, quantidade, dataEmissao);
                }
                ordens.enqueue(ordem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ordens;
    }

    public static void listarAcoes() {
        try (RandomAccessFile arquivo = new RandomAccessFile("ativos.txt", "r")) {
            while (arquivo.getFilePointer() < arquivo.length()) {
                int id = arquivo.readInt();
                String codigo = arquivo.readUTF();
                String empresa = arquivo.readUTF();
                double cotacao = arquivo.readDouble();

                System.out.println("Id: " + id);
                System.out.println("Codigo: " + codigo);
                System.out.println("Empresa: " + empresa);
                System.out.println("Cotacao: " + cotacao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
