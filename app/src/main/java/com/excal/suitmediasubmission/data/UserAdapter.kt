import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.excal.suitmediasubmission.data.Data
import com.excal.suitmediasubmission.data.UserData
import com.excal.suitmediasubmission.databinding.ItemRowUserBinding

class UserAdapter(  userData:UserData) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    var listener: onItemClick? = null
    var listUser = userData.data
    private lateinit var binding: ItemRowUserBinding

    inner class ListViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgPhoto: ImageView = binding.imgItemPhoto
        val tvName: TextView = binding.tvItemName
        val tvDescription: TextView = binding.tvItemEmail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (avatar, email, first_name, id, last_name) = listUser[position]

        holder.tvName.text = "$first_name $last_name"
        holder.tvDescription.text = email

        Glide.with(holder.itemView.context)
            .load(avatar)
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            listener?.setOnItemClick(it, listUser[position])
        }
    }


    interface onItemClick {
        fun setOnItemClick(view: View, user: Data)
    }
}