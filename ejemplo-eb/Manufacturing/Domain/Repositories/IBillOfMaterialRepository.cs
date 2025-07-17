using System.Threading.Tasks;
using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;

namespace ejemplo_eb.Manufacturing.Domain.Repository
{
    public interface IBillOfMaterialsItemRepository
    {
        Task<BillOfMaterialsItem?> FindByIdAsync(int id);
        Task AddAsync(BillOfMaterialsItem item);

        Task<bool> ExistsByCompositeKeyAsync(Guid itemProductNumber, int batchId, int billOfMaterialsId);

    }
}
