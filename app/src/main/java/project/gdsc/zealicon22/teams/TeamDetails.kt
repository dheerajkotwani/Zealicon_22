package project.gdsc.zealicon22.teams

/**
 * @Author: Karan Verma
 * @Date: 08/04/22
 */
data class TeamDetails(
    val teamsModal : TeamsModal
)

data class TeamsModal(
    val core: ArrayList<TeamsModalItem>,
    val management: ArrayList<TeamsModalItem>
)

data class TeamsModalItem(
    val image: String,
    val name: String,
    val position: String,
)