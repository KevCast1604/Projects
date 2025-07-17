using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;
using ejemplo_eb.Manufacturing.Domain.Repository;
using System.Threading.Tasks;
using ejemplo_eb.Manufacturing.Domain.Services;

namespace ejemplo_eb.Manufacturing.Application.Internal.QueryServices
{
    public class BillOfMaterialsItemQueryService : IBillOfMaterialsItemQueryService
    {
        private readonly IBillOfMaterialsItemRepository _repository;

        public BillOfMaterialsItemQueryService(IBillOfMaterialsItemRepository repository)
        {
            _repository = repository;
        }

        public async Task<BillOfMaterialsItem?> GetByIdAsync(int id)
        {
            return await _repository.FindByIdAsync(id);
        }

        // Puedes agregar más queries aquí si necesitas
    }
}
