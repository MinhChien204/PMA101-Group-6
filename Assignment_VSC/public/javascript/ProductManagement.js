const formProduct = document.getElementById( 'form-product' );
const image_cloth = document.getElementById( 'image_cloth' );
const name_cloth = document.getElementById( 'name_cloth' );
const price_cloth = document.getElementById( 'price_cloth' );
const brand = document.getElementById( 'brand' );
const mota = document.getElementById( 'mota' );
const tinhtrang = document.getElementById( 'tinhtrang' );
const idFruit = document.getElementById( 'idFruit' );
const list = document.getElementById( 'list' );

var fruits = [];
const fetchData = () =>
{
    fetch( 'http://localhost:3000/api/product' )
        .then( res => res.json() )
        .then( data =>
        {
            fruits = data.data;
            displayFruit();
        } )
        .catch( err => console.log( err ) );
}

const displayFruit = () =>
{
    list.innerHTML = `
        <tr>
            <th>Tên</th>
            <th>Giá</th>
            <th>Mô tả</th>
            <th>Ảnh</th>
            <th>Trạng thái</th>
            <th>Action</th>
        </tr>
        ${ fruits.map( fruit => `
            <tr>
                <td>${ fruit.name_cloth }</td>
                <td>${ fruit.price_cloth }</td>
                <td>${ fruit.mota }</td>
                <td class="fruit-image">
                    ${ fruit.brand.map( image => `<img src="${ image }" alt="Ảnh của ${ fruit.name_cloth }">` ).join( '' ) }
                </td>
                <td>${ fruit.tinhtrang }</td>
                <td>
                    <button class="edit-btn" data-id="${ fruit._id }">Edit</button>
                    <button class="delete-btn" data-id="${ fruit._id }">Delete</button>
                </td>
            </tr>
        `).join( '' ) }
    `;
}

fetchData();

const addFruit = async ( event ) =>
{
    event.preventDefault();

    let image_cloth_value = image_cloth.value;
    let name_cloth_value = name_cloth.value;
    let price_cloth_value = price_cloth.value;
    let brand_value = brand.value;
    let mota_value = mota.value;
    let tinhtrang_value = tinhtrang.value;

    try
    {
        if ( image_cloth_value === '' || name_cloth_value === '' || price_cloth_value === '' || brand_value === '' || mota_value === '' || tinhtrang_value === '' )
        {
            alert( 'Vui lòng điền đủ các trường' );
        } else
        {
            const formData = new FormData();
            formData.append( 'image_cloth', image_cloth.files[ 0 ] );
            formData.append( 'name_cloth', name_cloth_value );
            formData.append( 'price_cloth', Number( price_cloth_value ) );
            formData.append( 'brand', brand_value );
            formData.append( 'mota', mota_value );
            formData.append( 'tinhtrang', tinhtrang_value );
            for ( let i = 0; i < brand.files.length; i++ )
            {
                formData.append( 'image', brand.files[ i ] );
            }

            const response = await fetch( 'http://localhost:3000/api/product', {
                method: 'POST',
                body: formData
            } );

            const result = await response.json();

            if ( result.status === 200 )
            {
                alert( 'Thêm mới thành công' );
                fetchData();
                idFruit.value = '';
                formProduct.reset();
            } else
            {
                alert( 'Thêm mới thất bại error:' + result.status );
            }
        }
    } catch ( error )
    {
        console.error( 'Error:', error );
        alert( 'Đã xảy ra lỗi' );
    }
}

const deleteFruit = ( event ) =>
{
    const id = event.target.dataset.id;
    console.log( id );
    let result = confirm( 'Bạn có muốn xóa hay không?' );
    if ( result )
    {
        fetch( 'http://localhost:3000/api/destroy-fruit-by-id/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        } ).then( res =>
        {
            if ( res.status == 200 )
            {
                alert( 'Xóa thành công' );
                fetchData();
            } else
            {
                alert( 'Lỗi!!' + res.status );
            }
        } );
    }
}

const editFruit = ( event ) =>
{
    const id = event.target.dataset.id;
    const temp = fruits.find( fruit => fruit._id == id );
    name_cloth.value = temp.name_cloth;
    price_cloth.value = temp.price_cloth;
    mota.value = temp.mota;
    tinhtrang.value = temp.tinhtrang;
    idFruit.value = id;

    const dataTransfer = new DataTransfer();
    temp.brand.forEach( image =>
    {
        const file = new File( [ image ], image.split( '/' ).pop() );
        dataTransfer.items.add( file );
    } );
    const fileList = dataTransfer.files;
    image_cloth.files = fileList;
}

const updateFruit = async ( event ) =>
{
    event.preventDefault();
    console.log( idFruit.value );
    let name = name_cloth.value;
    let price = price_cloth.value;
    let mota_value = mota.value;
    let tinhtrang_value = tinhtrang.value;

    try
    {
        if ( name === '' || price === '' || mota_value === '' || tinhtrang_value === '' )
        {
            alert( 'Vui lòng điền đủ các trường' );
        } else
        {
            const formData = new FormData();
            formData.append( 'name_cloth', name );
            formData.append( 'price_cloth', Number( price ) );
            formData.append( 'mota', mota_value );
            formData.append( 'tinhtrang', tinhtrang_value );
            for ( let i = 0; i < image_cloth.files.length; i++ )
            {
                formData.append( 'image', image_cloth.files[ i ] );
            }

            const response = await fetch( 'http://localhost:3000/api/update-fruit-by-id/' + idFruit.value, {
                method: 'PUT',
                body: formData
            } );

            const result = await response.json();

            if ( result.status === 200 )
            {
                alert( 'Sửa thành công' );
                idFruit.value = '';
                formProduct.reset();
                fetchData();
            } else
            {
                alert( 'Sửa thất bại error:' + result.status );
                idFruit.value = '';
                formProduct.reset();
            }
        }
    } catch ( error )
    {
        console.error( 'Error:', error );
        alert( 'Đã xảy ra lỗi' );
    }
}

formProduct.addEventListener( 'submit', event =>
{
    if ( idFruit.value === '' )
    {
        addFruit( event );
    } else
    {
        updateFruit( event );
    }
} );

list.addEventListener( 'click', event =>
{
    if ( event.target.classList.contains( 'delete-btn' ) )
    {
        deleteFruit( event );
    }
    if ( event.target.classList.contains( 'edit-btn' ) )
    {
        editFruit( event );
    }
} );
