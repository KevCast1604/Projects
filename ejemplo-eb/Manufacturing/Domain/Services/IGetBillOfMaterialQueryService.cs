using System.Threading.Tasks;
using ejemplo_eb.Manufacturing.Domain.Model.Commands;
using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;

namespace ejemplo_eb.Manufacturing.Domain.Services
{
    public interface IBillOfMaterialsItemQueryService
    {
        Task<BillOfMaterialsItem?> GetByIdAsync(int id);

    }
}
