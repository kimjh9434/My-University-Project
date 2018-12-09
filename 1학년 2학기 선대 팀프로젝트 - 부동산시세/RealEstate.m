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
    case '����'
        Dong='����';
    case '������'
        Dong={'����';'������';'������';'��ġ��';'���';'�Ｚ��';'���';'������';'�Ż絿';'�б�����';'���ﵿ';'������';'�Ͽ���';'�ڰ';'û�㵿'};
    case '������'
        Dong={'����';'���ϵ�';'�����';'�浿';'���̵�';'���ϵ�';'���ϵ�';'������';'�ϻ絿';'õȣ��'};
    case '���ϱ�'
        Dong={'����';'�̾Ƶ�';'����';'������';'���̵�'};
    case'������'
        Dong={'����';'���絿';'��ȭ��';'���׵�';'���ص�';'���߻굿';'���̵�';'���';'��ȭ��';'��â��';'���';'���赿';'�ܹ߻굿';'ȭ�'};
    case'���Ǳ�'
        Dong={'����';'���⵿';'������';'��õ��';'�Ÿ���'};
    case'������'
        Dong={'����';'���嵿';'���ǵ�';'���ڵ�';'�ɵ�';'�ھ絿';'�߰';'ȭ�絿'};
    case'���α�'
        Dong={'����';'��������';'������';'��ô��';'���ε�';'�õ�';'�ŵ�����';'������';'�¼���';'õ�յ�';'�׵�'};
    case'��õ��'
        Dong={'����';'���굿';'���굿';'���ﵿ'};
    case'�����'
        Dong={'����';'������';'��赿';'���赿';'�߰赿';'�ϰ赿'};
    case'������'
        Dong={'����';'������';'���е�';'�ֹ���';'â��'};
    case'���빮��'
        Dong={'����';'��ʸ���';'�ż���';'��ε�';'�̹���';'��ȵ�';'����';'���⵿';'û������';'ȸ�⵿';'�ְ浿'};
    case'���۱�'
        Dong={'����';'�뷮����';'��浿';'���۵�';'����';'��絿';'�󵵵�';'�Ŵ�浿';'�漮��'};
    case'������'
        Dong={'����';'������';'������';'���굿';'���ε�';'���ﵿ';'��ȭ��';'������';'������';'������';'�����';'��ϵ�';'������';'���굿';'�Ű�����';'�ż���';'������';'������';'������';'������';'�밭��';'�ߵ�';'â����';'������';'���ߵ�';'������';'������'};
    case'���빮��'
        Dong={'����';'�����µ�';'��õ��';'��ŵ�';'������';'�̱ٵ�';'������';'�ϰ��µ�';'�Ͼ�����';'���̵�';'����';'��õ��';'��õ��';'âõ��';'õ����';'������';'����';'������';'ȫ����';'ȫ����'};
    case'���ʱ�'
        Dong={'����';'���';'������';'��赿';'���ʵ�';'�ſ���';'���絿';'���';'��鵿';'������';'�����'};
    case'������'
        Dong={'����';'��ȣ��';'������';'���嵿';'��ٵ�';'��սʸ���';'������';'������';'������';'��䵿';'������';'�Ͽսʸ���';'��絿';'ȫ�͵�'};
    case'���ϱ�'
        Dong={'����';'������';'���ϵ�';'������';'���ҹ���';'������';'�Ｑ��';'����';'������';'���ϵ�';'�Ⱦϵ�';'���';'������';'������';'���ϵ�';'�Ͽ��'};
    case'���ı�'
        Dong={'����';'������';'�ſ���';'��õ��';'������';'���̵�';'������';'���̵�';'���ĵ�';'��õ��';'���ݵ�';'��ǵ�';'������';'ǳ����'};
    case'��õ��'
        Dong={'����';'��';'�ſ���';'������'};
    case'��������'
        Dong={'����';'��굿';'�븲��';'������';'������';'�ű浿';'����';'��ȭ��';'���ǵ���';'��������'};
    case'��걸'
        Dong={'����';'������';'������';'������';'������';'������';'���ڵ�';'���赿';'������';'��õ��';'���赿';'������';'�Ű赿';'��â��';'�빮��';'��굿';'��ȿ��';'���̵�';'���¿���';'�ּ���';'û�ϵ�';'û�ĵ�';'�Ѱ���';'�ѳ���';'ȿâ��';'�ľϵ�'};
    case'����'
        Dong={'����';'������';'���굿';'�����';'������';'�ұ���';'������';'�Ż絿';'���̵�';'���ϵ�';'���굿';'������'};
    case'���α�'
        Dong={'����';'����';'������';'��';'�赿';'����';'������';'��ö��';'������';'���ϵ�';'���⵿';'������';'�ǳ�';'������';'������';'���ڵ�';'����';'���ϵ�';'���ֵ�';'���ŵ�';'���ǵ�';
        '������';'�����1��';'�����2��';'�����3��';'�����4��';'����';'���ǵ�';'���͵�';'�ξϵ�';'�簣��';'������';'��û��';'��';'������';'������';'�Ұݵ�';'�ۿ���';'������';'���۵�';'����1��';
        '����2��';'�ű���';'�Ź���1��';'�Ź���2��';'�ſ���';'�ȱ���';'���ǵ�';'������';'������';'���ε�';'�ͷ浿';'��ϵ�';'������';'������';'��ȭ��';'�ͼ���';'�λ絿';'���ǵ�';'��絿';'�絿';
        '������';'����1��';'����2��';'����3��';'����4��';'����5��';'����6��';'���е�';'â����';'â��1��';'â��2��';'â��3��';'û�';'û����';'ü�ε�';'��ŵ�';'���ǵ�';'���ε�';'���ǵ�';'��'
        '��â��';'�ʿ';'���̵�';'��ȭ��';'ȫ����';'ȫ�ĵ�';'ȭ��';'ȿ�ڵ�';'ȿ����';'������'};
    case'�߱�'
        Dong={'����';'����';'�Դ빮��';'���굿';'��â��';'���е�';'�ٵ�';'������';'��';'������';'���е�';'������';'��굿';'������';'��â��';'�긲��';'�ﰢ��';'���ҹ���';
        '�Ұ���';'��ǥ��';'���ϵ�';'��ȭ��';'�Ŵ絿';'�ָ���';'������';'���嵿';'���嵿';'������';'���ַ�';'������';'������';'�屳��';'���浿';'����';'����';'�ֱ���';
        '���ڵ�';'�߸���';'�ʵ�';'�湫��';'������';'�����';'�ʵ�';'Ȳ�е�';'ȸ����';'���ε�'};
    case'�߶���'
        Dong={'����';'���쵿';'���';'����';'�����';'�ų���';'��ȭ��'};
end
set(handles.popupmenu_Dong,'String',Dong);
set(handles.popupmenu_Dong,'Value',1);
set(handles.popupmenu_Apt,'String','����');
set(handles.popupmenu_Apt,'Value',1);
set(handles.popupmenu_Extent,'String','����');
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
    case'������'
        switch cell2mat(Dong)
            case '����'
                Apt='����';
            case '��ȣ��'
                Apt={'����';'��ȣ1��Ǫ������';'��ȣ�����';'��ȣ�Ｚ���̾�';'��ȣ����1��';'��ȣ����2��';'�λ�';'���̾����̸���';'�Ե�';'����';'������ȣ';'������ȣ2��';'���｣2��Ǫ������';'���｣Ǫ������';'�ű�ȣ�λ�����';'�ѽ����÷���'};
            case '������'
                Apt={'����';'��������';'�溸�̸�����';'�ص��׸�';'�ص�����';'���λ��κ�';'���̾ȿ���������';'��������ǳ�����̿�';'�������ص�';'�����Ｚ';'������︲';'����������';'��������';'����ũ��';'�ѳ�������'};
            case '���嵿'
                Apt={'����';'��ȣ ��︲';'�뼺���ϵ�';'���嵿�ż��̼�����';'���嵿����';'�Ｚ';'���1,2��';'����';'�Ÿ�ī�̺�';'���';'�߾�������';'�¼�'};
            case '��絿'
                Apt={'����';'�븲';'�λ�';'�λ�����';'���';'���｣���Ǫ������';'�ŵ���';'�̼������';'����ѽ�';'�������Ÿ��'};
            otherwise '����'
                Apt='����';
        end

    otherwise '����'
        Apt='����';
end
set(handles.popupmenu_Apt,'String',Apt);
set(handles.popupmenu_Apt,'Value',1);
set(handles.popupmenu_Extent,'String','����');
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
    case'������'
        switch cell2mat(Dong)
            case '��絿'
                switch cell2mat(Apt)
                    case '����'
                        Extent='����';
                    case'�븲'
                        Extent={'����';'82.64��/59.96��';'102.47��/84.87��';'135.53��/114.94��'};
                    case'�λ�'
                        Extent={'����';'79.33��/59.94��';'105.78��/84.87��'};
                    case'�λ�����'
                        Extent={'����';'82.55��/59.99��';'99.14��/70.79��';'107.62��/84.99��';'110.6��/84.99��';'143.33��/114.99��'};
                    case'���'
                        Extent={'����';'79.33��/67.92��';'99.17��/84.93��';'145.45��/122.01��'};
                    case'���｣���Ǫ������'
                        Extent={'����';'79.59��/59.68��';'82.97��/59.81��';'111.38B��/84.74��';'111.11A��/84.81��';'143.75A��/114.33��'};    
                    case'�ŵ���'
                        Extent={'����';'82.64��/59.94��';'95.86��/76.41��';'103.47��/84.99��';'138.84��/114.96��'};    
                    case'�̼������'
                        Extent={'����';'76.03��/59.26��';'102.47��/84.94��';'138.84��/114.76��'};  
                    case'����ѽ�'
                        Extent={'����';'79.33A��/59.91��';'79.33B��/59.94��';'105.78��/84.95��';'142.14��/114.97��'};  
                    case'�������Ÿ��'
                        Extent={'����';'85.95��/59.96��';'109.09��/84.71��';'145.45��/114.62��'}; 
                end
                
            otherwise '����'
                Extent='����';
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

if strcmp (Extent,'����')~=1
    switch cell2mat(Gu)  
        case'������'      
            switch cell2mat(Dong)          
                case '��絿'               
                    switch cell2mat(Apt)                   
                        case'�븲'                       
                            load Dealim
                            %Extent={'����';'82.64��/59.96��';'102.47��/84.87��';'135.53��/114.94��'};                     
                            switch cell2mat(Extent)                         
                                case '82.64��/59.96��'
                                    data=Dealim(:,:,1);                         
                                case '102.47��/84.87��'                              
                                    data=Dealim(:,:,2);                        
                                case '135.53��/114.94��'                              
                                    data=Dealim(:,:,3);                    
                            end                            
                        case'�λ�'
                            %Extent={'����';'79.33��/59.94��';'105.78��/84.87��'};                 
                        case'�λ�����'                       
                            %Extent={'����';'82.55��/59.99��';'99.14��/70.79��';'107.62��/84.99��';'110.6��/84.99��';'143.33��/114.99��'};
                        case'���'                      
                            %Extent={'����';'79.33��/67.92��';'99.17��/84.93��';'145.45��/122.01��'};
                        case'���｣���Ǫ������'                       
                            %Extent={'����';'79.59��/59.68��';'82.97��/59.81��';'111.38B��/84.74��';'111.11A��/84.81��';'143.75A��/114.33��'};                       
                        case'�ŵ���'
                            %Extent={'����';'82.64��/59.94��';'95.86��/76.41��';'103.47��/84.99��';'138.84��/114.96��'};    
                        case'�̼������'                       
                            %Extent={'����';'76.03��/59.26��';'102.47��/84.94��';'138.84��/114.76��'};                   
                        case'����ѽ�'       
                            %Extent={'����';'79.33A��/59.91��';'79.33B��/59.94��';'105.78��/84.95��';'142.14��/114.97��'};  
                        case'�������Ÿ��'
                            %Extent={'����';'85.95��/59.96��';'109.09��/84.71��';'145.45��/114.62��'};        
                    end                                        
                otherwise '����'               
                    Extent='����';     
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
