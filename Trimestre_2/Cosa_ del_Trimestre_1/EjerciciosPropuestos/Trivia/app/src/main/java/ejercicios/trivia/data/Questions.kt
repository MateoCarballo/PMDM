package ejercicios.trivia.data

import ejercicios.trivia.model.Question

class Questions {
    companion object {
        private val f1Questions: List<Question> = listOf(
            Question(
                questionText = "¿Quién ganó el Campeonato Mundial de Fórmula 1 en 2004?",
                options = listOf(
                    "Fernando Alonso",
                    "Michael Schumacher",
                    "Kimi Räikkönen",
                    "Jenson Button"
                ),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿En qué año ganó Lewis Hamilton su primer título de F1?",
                options = listOf("2007", "2008", "2009", "2010"),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿Qué equipo terminó con el dominio de Red Bull en 2014?",
                options = listOf("Ferrari", "Mercedes", "McLaren", "Renault"),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿Quién ganó el Campeonato de F1 de 2016 tras una intensa lucha con Lewis Hamilton?",
                options = listOf(
                    "Sebastian Vettel",
                    "Nico Rosberg",
                    "Max Verstappen",
                    "Daniel Ricciardo"
                ),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿Cuántos campeonatos mundiales ha ganado Fernando Alonso hasta 2024?",
                options = listOf("1", "2", "3", "4"),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿Qué equipo dominó la Fórmula 1 entre 2014 y 2020?",
                options = listOf("Red Bull", "Ferrari", "Mercedes", "McLaren"),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "¿Quién ganó el campeonato de 2021 tras una intensa batalla con Lewis Hamilton?",
                options = listOf(
                    "Valtteri Bottas",
                    "Max Verstappen",
                    "Charles Leclerc",
                    "Sergio Pérez"
                ),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿En qué año Red Bull volvió a la cima con Max Verstappen ganando el título?",
                options = listOf("2019", "2020", "2021", "2022"),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "¿Qué piloto tiene el récord de más pole positions en la historia de la F1 hasta 2024?",
                options = listOf(
                    "Ayrton Senna",
                    "Lewis Hamilton",
                    "Michael Schumacher",
                    "Sebastian Vettel"
                ),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿Cuántos campeonatos mundiales ganó Sebastian Vettel con Red Bull?",
                options = listOf("2", "3", "4", "5"),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "¿Con qué equipo ganó Fernando Alonso su última carrera antes de 2024?",
                options = listOf("Ferrari", "McLaren", "Alpine", "Aston Martin"),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "¿Qué piloto consiguió su primera victoria en Fórmula 1 en el Gran Premio de Italia 2020?",
                options = listOf("Pierre Gasly", "George Russell", "Carlos Sainz", "Lando Norris"),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "¿Quién consiguió la primera victoria de Ferrari en la era híbrida (2014-2024)?",
                options = listOf(
                    "Charles Leclerc",
                    "Fernando Alonso",
                    "Sebastian Vettel",
                    "Carlos Sainz"
                ),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "¿En qué año se introdujo el sistema de puntos 25-18-15 en la F1?",
                options = listOf("2009", "2010", "2011", "2012"),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿Qué equipo ganó su primera carrera en F1 en 2021 con Esteban Ocon?",
                options = listOf("Aston Martin", "Alpine", "AlphaTauri", "Haas"),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "¿En qué año McLaren logró su última victoria en F1 antes de 2024?",
                options = listOf("2010", "2012", "2021", "2023"),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "¿Qué piloto sufrió un grave accidente en el Gran Premio de Bahréin 2020?",
                options = listOf(
                    "Romain Grosjean",
                    "Kevin Magnussen",
                    "Nico Hülkenberg",
                    "Lance Stroll"
                ),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "¿Cuál fue la primera carrera nocturna en la historia de la Fórmula 1?",
                options = listOf("Singapur", "Baréin", "Abu Dhabi", "Las Vegas"),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "¿Cuántos campeonatos ha ganado Red Bull entre 2010 y 2024?",
                options = listOf("5", "6", "7", "8"),
                correctAnswerIndex = 3
            ),
            Question(
                questionText = "¿Qué piloto ha ganado más carreras en una sola temporada hasta 2024?",
                options = listOf(
                    "Michael Schumacher",
                    "Sebastian Vettel",
                    "Lewis Hamilton",
                    "Max Verstappen"
                ),
                correctAnswerIndex = 3
            )
        )
        fun getRandomQuestion(): Question{
            return f1Questions[(0..19).random()]
        }
        fun getOneQuestion(index: Int): Question{
            return f1Questions[index]
        }
    }
}
