package hr.foi.rmai.mypetpal.helpers

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rmai.mypetpal.PetProfileActivity
import hr.foi.rmai.mypetpal.R
import hr.foi.rmai.mypetpal.entities.UserPet

class PetListAdapter(private val userPetList: List<UserPet>) :
    RecyclerView.Adapter<PetListAdapter.PetViewHolder>() {

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petName: TextView = itemView.findViewById(R.id.pet_name)
        val openPetButton: Button = itemView.findViewById(R.id.open_pet_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val currentUserPet = userPetList[position]
        holder.petName.text = currentUserPet.petName
        holder.openPetButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, PetProfileActivity::class.java).apply {
                putExtra("PET_ID", currentUserPet.petId)
                putExtra("PET_NAME", currentUserPet.petName)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = userPetList.size
}