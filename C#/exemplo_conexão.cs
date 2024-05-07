using System;
using System.Data.SqlClient;

public class DatabaseConnector
{
    private string connectionString;

    public DatabaseConnector()
    {
        //só mudar pros nomes de verdade do banco de dados
        string server = "server_address";
        string database = "database_name";
        string user = "username";
        string password = "password";
        int port = 1433;

        connectionString = $"Server={server},{port};Database={database};User Id={user};Password={password};";
    }

    public void TestConnection()
    {
        try
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();
                Console.WriteLine("Conexão estabelecida");

                //um exemplo de consulta
                string query = "SELECT * FROM uma_tabela_qualquer"; //selecionou tudo da tabela "uma_tabela_qualquer"
                SqlCommand command = new SqlCommand(query, connection); //"criou" o comando
                SqlDataReader reader = command.ExecuteReader(); //executou o comando, a função "executereader" é nativa da biblioteca SqlClient

                while (reader.Read())
                {
                    Console.WriteLine(reader["colunda_da_uma_tabela_qualquer"].ToString()); //imprimiu o que vc achou da sua query, no caso vai imprimir todos os dados (todos os atributos de todas colunas) da tabela "uma_tab..."
                }
                reader.Close();
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine("Erro ao conectar ao banco de dados: " + ex.Message);
        }
    }
}

public class Program
{
    public static void Main(string[] args)
    {
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.TestConnection(); //num caso real, isso testaria a conexão, mas como eu fiz só pra ser um exemplo, o código nem compilar vai, pra compilar tem que gerar um projeto, ter de fato o banco de dados, muitas paradinhas chatinhas pra funcionar
    }
}
