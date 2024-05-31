package edu.joverpenalva.proyectomoviles.ui.main


import android.graphics.Color
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.Color.YELLOW
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.joverpenalva.proyectomoviles.databinding.TrabajoLayoutBinding
import edu.joverpenalva.proyectomoviles.model.trabajos.Result

class TrabajosAdapter(
    val onClickTrabajo: (trabajo: Result) -> Unit,
): ListAdapter<Result, TrabajosAdapter.TrabajosViewHolder>(TrabajosDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrabajosViewHolder {
        return TrabajosViewHolder(
            TrabajoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: TrabajosViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TrabajosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val bind = TrabajoLayoutBinding.bind(view)
        fun bind(trabajo: Result){
            bind.tvCodTrabajo.text = trabajo.codTrabajo
            bind.tvDescripcion.text = trabajo.descripcion

            if (trabajo.prioridad == 1) {
                bind.tvPrioridad.setTextColor(RED)
            } else if (trabajo.prioridad == 2) {
                bind.tvPrioridad.setTextColor(Color.parseColor("#FFA500"))
            } else if (trabajo.prioridad == 3) {
                bind.tvPrioridad.setTextColor(YELLOW)
            } else {
                bind.tvPrioridad.setTextColor(GREEN)
            }

            bind.tvPrioridad.text = trabajo.prioridad.toString()

            itemView.setOnClickListener {
                onClickTrabajo(trabajo)
            }
        }
    }
}

class TrabajosDiffCallback: DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.codTrabajo == newItem.codTrabajo
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}