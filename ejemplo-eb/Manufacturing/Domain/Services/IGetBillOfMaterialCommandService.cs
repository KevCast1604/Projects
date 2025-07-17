using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;
using ejemplo_eb.Manufacturing.Domain.Model.Commands;


namespace ejemplo_eb.Manufacturing.Domain.Services
{
    public interface IBillOfMaterialsItemCommandService
    {
        Task<BillOfMaterialsItem> CreateAsync(CreateBillOfMaterialsItemCommand command);
    }
}

