import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.bumptech.glide.Glide
import com.icarus.recycle_app.databinding.ItemSearchBinding
import com.icarus.recycle_app.dto.Trash
import java.util.Locale


class SearchListAdapter(private val context: Context, itemList: List<Trash>) :
    BaseAdapter(), Filterable {
    private val itemList: List<Trash>
    private val filteredList // 필터링된 결과를 저장할 리스트
            : MutableList<Trash>

    init {
        this.itemList = itemList
        filteredList = ArrayList(itemList) // 초기에는 전체 리스트가 보여지도록 설정
    }

    override fun getCount(): Int {
        return filteredList.size
    }

    override fun getItem(position: Int): Any {
        return filteredList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            ItemSearchBinding.inflate(inflater, parent, false)
        } else {
            // convertView가 이미 존재할 경우 뷰 바인딩을 사용하여 바인딩 인스턴스를 가져옵니다.
            ItemSearchBinding.bind(convertView)
        }

        val item = filteredList[position]
        binding.tvTitle.text = item.name

        Glide.with(context)
            .load(item.image) // item.image는 이미지 파일의 경로 문자열
            .into(binding.ivImage)

        // 여기에서 다른 필요한 데이터를 설정할 수 있습니다.

        return binding.root
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val filteredItems: MutableList<Trash> = ArrayList<Trash>()
                if (constraint.isNullOrEmpty()) {
                    // 제약조건이 없을 경우 전체 아이템을 보여줍니다.
                    filteredItems.addAll(itemList)
                } else {
                    val filterPattern =
                        constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                    for (item in itemList) {
                        if (item.name.lowercase(Locale.ROOT).contains(filterPattern)) {
                            filteredItems.add(item)
                        }
                    }
                }
                results.values = filteredItems
                results.count = filteredItems.size
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                filteredList.clear()
                filteredList.addAll((results.values as List<Trash>))
                notifyDataSetChanged()
            }
        }
    }
}