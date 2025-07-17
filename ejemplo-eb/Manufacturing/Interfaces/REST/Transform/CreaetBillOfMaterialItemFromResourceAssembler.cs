using ejemplo_eb.Manufacturing.Domain.Model.Commands;
using ejemplo_eb.Manufacturing.Interfaces.REST.Resources;
using System;

namespace ejemplo_eb.Manufacturing.Interfaces.REST.Transforms
{
    public static class CreateBillOfMaterialItemFromResourceAssembler
    {
        public static CreateBillOfMaterialsItemCommand ToCommand(this CreateBillOfMaterialsItemResource resource)
        {
            return new CreateBillOfMaterialsItemCommand(
                resource.BillOfMaterialsId,
                Guid.Parse(resource.ItemProductNumber),
                resource.BatchId,
                resource.RequiredQuantity,
                resource.ScheduledStartAt,
                resource.RequiredAt
            );
        }
    }
}
