function varargout = RealEstate(varargin)
% REALESTATE M-file for RealEstate.fig
%      REALESTATE, by itself, creates a new REALESTATE or raises the existing
%      singleton*.
%
%      H = REALESTATE returns the handle to a new REALESTATE or the handle to
%      the existing singleton*.
%
%      REALESTATE('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in REALESTATE.M with the given input arguments.
%
%      REALESTATE('Property','Value',...) creates a new REALESTATE or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before RealEstate_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to RealEstate_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help RealEstate

% Last Modified by GUIDE v2.5 14-Dec-2013 21:12:01

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @RealEstate_OpeningFcn, ...
                   'gui_OutputFcn',  @RealEstate_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before RealEstate is made visible.
function RealEstate_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to RealEstate (see VARARGIN)

% Choose default command line output for RealEstate
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);
set(handles.checkbox1,'Value',1);

% UIWAIT makes RealEstate wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = RealEstate_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;


% --- Executes on selection change in popupmenu_Si.
function popupmenu_Si_Callback(hObject, eventdata, handles)



% --- Executes during object creation, after setting all properties.
function popupmenu_Si_CreateFcn(hObject, eventdata, handles)

if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on selection change in popupmenu_Gu.
function popupmenu_Gu_Callback(hObject, eventdata, handles)
contents_Gu = get(handles.popupmenu_Gu,'String');
Gu = contents_Gu(get(handles.popupmenu_Gu,'Value'));

switch cell2mat(Gu)
    case '선택'
        Dong='선택';
    case '강남구'
        Dong={'선택';'개포동';'논현동';'대치동';'도곡동';'삼성동';'세곡동';'수서동';'신사동';'압구정동';'역삼동';'율현동';'일원동';'자곡동';'청담동'};
    case '강동구'
        Dong={'선택';'강일동';'고덕동';'길동';'둔촌동';'명일동';'상일동';'성내동';'암사동';'천호동'};
    case '강북구'
        Dong={'선택';'미아동';'번동';'수유동';'우이동'};
    case'강서구'
        Dong={'선택';'가양동';'개화동';'공항동';'과해동';'내발산동';'등촌동';'마곡동';'방화동';'염창동';'오곡동';'오쇠동';'외발산동';'화곡동'};
    case'관악구'
        Dong={'선택';'난향동';'남현동';'봉천동';'신림동'};
    case'광진구'
        Dong={'선택';'관장동';'구의동';'군자동';'능동';'자양동';'중곡동';'화양동'};
    case'구로구'
        Dong={'선택';'가리봉동';'개봉동';'고척동';'구로동';'궁동';'신도림동';'오류동';'온수동';'천왕동';'항동'};
    case'금천구'
        Dong={'선택';'가산동';'독산동';'시흥동'};
    case'노원구'
        Dong={'선택';'공릉동';'상계동';'월계동';'중계동';'하계동'};
    case'도봉구'
        Dong={'선택';'도봉동';'방학동';'쌍문동';'창동'};
    case'동대문구'
        Dong={'선택';'답십리동';'신설동';'용두동';'이문동';'장안동';'전농동';'제기동';'청량리동';'회기동';'휘경동'};
    case'동작구'
        Dong={'선택';'노량진동';'대방동';'동작동';'본동';'사당동';'상도동';'신대방동';'흑석동'};
    case'마포구'
        Dong={'선택';'공덕동';'구수동';'노고산동';'당인동';'대흥동';'도화동';'동교동';'마포동';'망원동';'상수동';'상암동';'서교동';'성산동';'신공덕동';'신수동';'신정동';'아현동';'연남동';'연리동';'용강동';'중동';'창전동';'토정동';'하중동';'합정동';'현석동'};
    case'서대문구'
        Dong={'선택';'남가좌동';'냉천동';'대신동';'대현동';'미근동';'봉원동';'북가좌동';'북아현동';'신촌동';'연희동';'영천동';'옥천동';'창천동';'천연동';'충정로';'동합';'현저동';'홍은동';'홍제동'};
    case'서초구'
        Dong={'선택';'내곡동';'반포동';'방배동';'서초동';'신원동';'양재동';'염곡동';'우면동';'원지동';'잠원동'};
    case'성동구'
        Dong={'선택';'금호동';'도선동';'마장동';'사근동';'상왕십리동';'성수동';'송정동';'옥수동';'용답동';'응봉동';'하왕십리동';'행당동';'홍익동'};
    case'성북구'
        Dong={'선택';'길음동';'돈암동';'동선동';'동소문동';'보문동';'삼선동';'상월곡동';'석관동';'성북동';'안암동';'월곡동';'장위동';'정릉동';'종암동';'하월곡동'};
    case'송파구'
        Dong={'선택';'가락동';'거여동';'마천동';'문정동';'방이동';'삼전동';'석촌동';'송파동';'신천동';'오금동';'잠실동';'장지동';'풍납동'};
    case'양천구'
        Dong={'선택';'목동';'신월동';'신정동'};
    case'영등포구'
        Dong={'선택';'당산동';'대림동';'도림동';'문래동';'신길동';'양평동';'양화동';'여의도동';'영등포동'};
    case'용산구'
        Dong={'선택';'갈월동';'남영동';'도원동';'도원동';'동빙고동';'동자동';'문배동';'보광동';'산천동';'서계동';'서빙고동';'신계동';'신창동';'용문동';'용산동';'원효로';'이촌동';'이태원동';'주성동';'청암동';'청파동';'한강로';'한남동';'효창동';'후암동'};
    case'은평구'
        Dong={'선택';'갈현동';'구산동';'녹번동';'대조동';'불광동';'수색동';'신사동';'역촌동';'응암동';'증산동';'진관동'};
    case'종로구'
        Dong={'선택';'가희동';'견지동';'경운동';'계동';'공평동';'관수동';'관철동';'교남동';'교북동';'구기동';'궁정동';'권농동';'낙원동';'내수동';'내자동';'누상동';'누하동';'당주동';'도렴동';'돈의동';
        '동숭동';'명륜동1가';'명륜동2가';'명륜동3가';'명륜동4가';'묘동';'무악동';'봉익동';'부암동';'사간동';'사직동';'삼청동';'동';'서린동';'세종로';'소격동';'송월동';'송현동';'수송동';'숭인1동';
        '숭인2동';'신교동';'신문로1가';'신문로2가';'신영동';'안국동';'연건동';'연지동';'예지동';'옥인동';'와룡동';'운니동';'원남동';'원서동';'이화동';'익선동';'인사동';'인의동';'장사동';'재동';
        '적선동';'종로1가';'종로2가';'종로3가';'종로4가';'종로5가';'종로6가';'중학동';'창성동';'창신1동';'창신2동';'창신3동';'청운동';'청진동';'체부동';'충신동';'통의동';'통인동';'팔판동';'평동'
        '평창동';'필운동';'행촌동';'혜화동';'홍지동';'홍파동';'화동';'효자동';'효제동';'훈정동'};
    case'중구'
        Dong={'선택';'광희동';'님대문로';'남산동';'남창동';'남학동';'다동';'만리동';'명동';'무교동';'무학동';'묵정동';'방산동';'봉래동';'북창동';'산림동';'삼각동';'서소문동';
        '소공동';'수표동';'수하동';'순화동';'신당동';'쌍림동';'예관동';'예장동';'오장동';'을지로';'의주로';'인현동';'입정동';'장교동';'장충동';'저동';'정동';'주교동';
        '주자동';'중림동';'초동';'충무로';'충정로';'태평로';'필동';'황학동';'회현동';'흥인동'};
    case'중랑구'
        Dong={'선택';'망우동';'면목동';'묵동';'상봉동';'신내동';'중화동'};
end
set(handles.popupmenu_Dong,'String',Dong);
set(handles.popupmenu_Dong,'Value',1);
set(handles.popupmenu_Apt,'String','선택');
set(handles.popupmenu_Apt,'Value',1);
set(handles.popupmenu_Extent,'String','선택');
set(handles.popupmenu_Extent,'Value',1);

% --- Executes during object creation, after setting all properties.
function popupmenu_Gu_CreateFcn(hObject, eventdata, handles)
% hObject    handle to popupmenu_Gu (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: popupmenu controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on selection change in popupmenu_Dong.
function popupmenu_Dong_Callback(hObject, eventdata, handles)
contents_Gu = get(handles.popupmenu_Gu,'String');
Gu = contents_Gu(get(handles.popupmenu_Gu,'Value'));

contents_Dong = get(handles.popupmenu_Dong,'String');
Dong = contents_Dong(get(handles.popupmenu_Dong,'Value'));

switch cell2mat(Gu)
    case'성동구'
        switch cell2mat(Dong)
            case '선택'
                Apt='선택';
            case '금호동'
                Apt={'선택';'금호1차푸르지오';'금호동대우';'금호삼성래미안';'금호자이1차';'금호자이2차';'두산';'래미안하이리버';'롯데';'벽산';'브라운스톤금호';'브라운스톤금호2차';'서울숲2차푸르지오';'서울숲푸르지오';'신금호두산위브';'한신休플러스'};
            case '옥수동'
                Apt={'선택';'강변벽산';'경보이리스힐';'극동그린';'극동옥정';'동인샤인빌';'래미안옥수리버젠';'옥수강변풍림아이원';'옥수동극동';'옥수삼성';'옥수어울림';'옥수하이츠';'옥수현대';'이테크빌';'한남하이츠'};
            case '마장동'
                Apt={'선택';'금호 어울림';'대성유니드';'마장동신성미소지움';'마장동현대';'삼성';'삼웅1,2차';'세림';'신명스카이뷰';'장원';'중앙하이츠';'태성'};
            case '행당동'
                Apt={'선택';'대림';'두산';'두산위브';'삼부';'서울숲행당푸르지오';'신동아';'이수브라운스톤';'행당한신';'행당한진타운'};
            otherwise '선택'
                Apt='선택';
        end

    otherwise '선택'
        Apt='선택';
end
set(handles.popupmenu_Apt,'String',Apt);
set(handles.popupmenu_Apt,'Value',1);
set(handles.popupmenu_Extent,'String','선택');
set(handles.popupmenu_Extent,'Value',1);


% --- Executes during object creation, after setting all properties.
function popupmenu_Dong_CreateFcn(hObject, eventdata, handles)
% hObject    handle to popupmenu_Dong (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: popupmenu controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on selection change in popupmenu_Apt.
function popupmenu_Apt_Callback(hObject, eventdata, handles)
contents_Gu = get(handles.popupmenu_Gu,'String');
Gu = contents_Gu(get(handles.popupmenu_Gu,'Value'));

contents_Dong = get(handles.popupmenu_Dong,'String');
Dong = contents_Dong(get(handles.popupmenu_Dong,'Value'));

contents_Apt = get(handles.popupmenu_Apt,'String');
Apt = contents_Apt(get(handles.popupmenu_Apt,'Value'));

switch cell2mat(Gu)
    case'성동구'
        switch cell2mat(Dong)
            case '행당동'
                switch cell2mat(Apt)
                    case '선택'
                        Extent='선택';
                    case'대림'
                        Extent={'선택';'82.64㎡/59.96㎡';'102.47㎡/84.87㎡';'135.53㎡/114.94㎡'};
                    case'두산'
                        Extent={'선택';'79.33㎡/59.94㎡';'105.78㎡/84.87㎡'};
                    case'두산위브'
                        Extent={'선택';'82.55㎡/59.99㎡';'99.14㎡/70.79㎡';'107.62㎡/84.99㎡';'110.6㎡/84.99㎡';'143.33㎡/114.99㎡'};
                    case'삼부'
                        Extent={'선택';'79.33㎡/67.92㎡';'99.17㎡/84.93㎡';'145.45㎡/122.01㎡'};
                    case'서울숲행당푸르지오'
                        Extent={'선택';'79.59㎡/59.68㎡';'82.97㎡/59.81㎡';'111.38B㎡/84.74㎡';'111.11A㎡/84.81㎡';'143.75A㎡/114.33㎡'};    
                    case'신동아'
                        Extent={'선택';'82.64㎡/59.94㎡';'95.86㎡/76.41㎡';'103.47㎡/84.99㎡';'138.84㎡/114.96㎡'};    
                    case'이수브라운스톤'
                        Extent={'선택';'76.03㎡/59.26㎡';'102.47㎡/84.94㎡';'138.84㎡/114.76㎡'};  
                    case'행당한신'
                        Extent={'선택';'79.33A㎡/59.91㎡';'79.33B㎡/59.94㎡';'105.78㎡/84.95㎡';'142.14㎡/114.97㎡'};  
                    case'행당한진타운'
                        Extent={'선택';'85.95㎡/59.96㎡';'109.09㎡/84.71㎡';'145.45㎡/114.62㎡'}; 
                end
                
            otherwise '선택'
                Extent='선택';
        end
end
set(handles.popupmenu_Extent,'String',Extent);
set(handles.popupmenu_Extent,'Value',1);


% --- Executes during object creation, after setting all properties.
function popupmenu_Apt_CreateFcn(hObject, eventdata, handles)
% hObject    handle to popupmenu_Apt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: popupmenu controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on selection change in popupmenu_Extent.
function popupmenu_Extent_Callback(hObject, eventdata, handles)
% hObject    handle to popupmenu_Extent (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: contents = cellstr(get(hObject,'String')) returns popupmenu_Extent contents as cell array
%        contents{get(hObject,'Value')} returns selected item from popupmenu_Extent


% --- Executes during object creation, after setting all properties.
function popupmenu_Extent_CreateFcn(hObject, eventdata, handles)
% hObject    handle to popupmenu_Extent (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: popupmenu controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in pushbutton_Search.
function pushbutton_Search_Callback(hObject, eventdata, handles)
contents_Gu = get(handles.popupmenu_Gu,'String');
Gu = contents_Gu(get(handles.popupmenu_Gu,'Value'));

contents_Dong = get(handles.popupmenu_Dong,'String');
Dong = contents_Dong(get(handles.popupmenu_Dong,'Value'));

contents_Apt = get(handles.popupmenu_Apt,'String');
Apt = contents_Apt(get(handles.popupmenu_Apt,'Value'));

contents_Extent = get(handles.popupmenu_Extent,'String');
Extent = contents_Extent(get(handles.popupmenu_Extent,'Value'));

if strcmp (Extent,'선택')~=1
    switch cell2mat(Gu)  
        case'성동구'      
            switch cell2mat(Dong)          
                case '행당동'               
                    switch cell2mat(Apt)                   
                        case'대림'                       
                            load Dealim
                            %Extent={'선택';'82.64㎡/59.96㎡';'102.47㎡/84.87㎡';'135.53㎡/114.94㎡'};                     
                            switch cell2mat(Extent)                         
                                case '82.64㎡/59.96㎡'
                                    data=Dealim(:,:,1);                         
                                case '102.47㎡/84.87㎡'                              
                                    data=Dealim(:,:,2);                        
                                case '135.53㎡/114.94㎡'                              
                                    data=Dealim(:,:,3);                    
                            end                            
                        case'두산'
                            %Extent={'선택';'79.33㎡/59.94㎡';'105.78㎡/84.87㎡'};                 
                        case'두산위브'                       
                            %Extent={'선택';'82.55㎡/59.99㎡';'99.14㎡/70.79㎡';'107.62㎡/84.99㎡';'110.6㎡/84.99㎡';'143.33㎡/114.99㎡'};
                        case'삼부'                      
                            %Extent={'선택';'79.33㎡/67.92㎡';'99.17㎡/84.93㎡';'145.45㎡/122.01㎡'};
                        case'서울숲행당푸르지오'                       
                            %Extent={'선택';'79.59㎡/59.68㎡';'82.97㎡/59.81㎡';'111.38B㎡/84.74㎡';'111.11A㎡/84.81㎡';'143.75A㎡/114.33㎡'};                       
                        case'신동아'
                            %Extent={'선택';'82.64㎡/59.94㎡';'95.86㎡/76.41㎡';'103.47㎡/84.99㎡';'138.84㎡/114.96㎡'};    
                        case'이수브라운스톤'                       
                            %Extent={'선택';'76.03㎡/59.26㎡';'102.47㎡/84.94㎡';'138.84㎡/114.76㎡'};                   
                        case'행당한신'       
                            %Extent={'선택';'79.33A㎡/59.91㎡';'79.33B㎡/59.94㎡';'105.78㎡/84.95㎡';'142.14㎡/114.97㎡'};  
                        case'행당한진타운'
                            %Extent={'선택';'85.95㎡/59.96㎡';'109.09㎡/84.71㎡';'145.45㎡/114.62㎡'};        
                    end                                        
                otherwise '선택'               
                    Extent='선택';     
            end            
    end
    number=[1:data(end)]';
    set(handles.date_start,'String','200401');
    set(handles.date_last,'String','201312');
    
    set(handles.trade_down,'String',num2str(data(1,1)) );
    set(handles.trade_nomal,'String',num2str(data(1,2)) );
    set(handles.trade_up,'String',num2str(data(1,3)) );
    set(handles.lease_down,'String',num2str(data(1,4)) );
    set(handles.lease_nomal,'String',num2str(data(1,5)) );
    set(handles.lease_up,'String',num2str(data(1,6)) );
    set(handles.deposit,'String',num2str(data(1,7)) );
    set(handles.monthlyRent,'String',[num2str(data(1,8)),' ~ ', num2str(data(1,9))] );
    
    trade=data(:,1:3);
    lease=data(:,4:7);
    monthlyRent=data(:,8:9);
    plot(handles.axes1,number,trade);
    axis([number(end),number(1),min(data(:,1))-2000,max(data(:,3))+2000]);
end


function date_start_Callback(hObject, eventdata, handles)
date_start=str2double(get(handles.date_start,'String'));
date_last=str2double(get(handles.date_last,'String'));

if date_start>200401
    date_start=200401;
    
elseif date_last>date_start
    if date_start-100>200401
        date_start=200401;
    else
        date_start=date_last-100;
    end
end    
%axis([date_start,date_last,min(data(:,2))-2000,max(data(:,4))+2000]);
set(handles.date_start,'String',double2str(date_start));
axis([date_start,date_last,20000,70000]);




% --- Executes during object creation, after setting all properties.
function date_start_CreateFcn(hObject, eventdata, handles)
% hObject    handle to date_start (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function date_last_Callback(hObject, eventdata, handles)
date_start=str2double(get(handles.date_start,'String'));
date_last=str2double(get(handles.date_last,'String'));

if date_last>201312
    date_last=201312;
    
elseif date_last<date_start
    if date_start+100>201312
        date_last=201312;
    else
        date_last=date_start+100;
    end
end    
%axis([date_start,date_last,min(data(:,2))-2000,max(data(:,4))+2000]);
set(handles.date_last,'String',double2str(date_last));
axis([date_start,date_last,20000,70000]);





% --- Executes during object creation, after setting all properties.
function date_last_CreateFcn(hObject, eventdata, handles)
% hObject    handle to date_last (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in radiobutton1.
function radiobutton1_Callback(hObject, eventdata, handles)
check=get(handles.radiobutton1,'Value')

if check
    hold on
else
    hold off
end


% --- Executes on button press in checkbox1.
function checkbox1_Callback(hObject, eventdata, handles)
% hObject    handle to checkbox1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of checkbox1


% --- Executes on button press in checkbox2.
function checkbox2_Callback(hObject, eventdata, handles)
% hObject    handle to checkbox2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of checkbox2


% --- Executes on button press in checkbox3.
function checkbox3_Callback(hObject, eventdata, handles)
% hObject    handle to checkbox3 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of checkbox3


% --- If Enable == 'on', executes on mouse press in 5 pixel border.
% --- Otherwise, executes on mouse press in 5 pixel border or over text3.
function text3_ButtonDownFcn(hObject, eventdata, handles)
% hObject    handle to text3 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
